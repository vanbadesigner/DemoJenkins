package com.vanba.stock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.vanba.stock.dao.ProductDao;
import com.vanba.stock.dao.impl.ProductDaoImpl;
import com.vanba.stock.model.Product;
import java.util.List;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.MappingDispatchAction;

public class ProductController extends MappingDispatchAction {

    private final static String SUCCESS = "success";
    private final static String FAIL = "failure";
    private ProductDao productDao;

    public ProductController() {
        productDao = ProductDaoImpl.getInstance();
    }

    public void setProductsForRequest(HttpServletRequest request) {
        List<Product> listProducts = productDao.getAllProducts();
        request.setAttribute("listProducts", listProducts);
    }

    public ActionForward toProductsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setProductsForRequest(request);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward toAddProductForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (form != null) {
            Product product = (Product) form;
            product.reset();
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward toUpdateProductForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String productIdString = request.getParameter("productId");
        String returnValue = FAIL;
        if (productIdString != null && !"".equals(productIdString)) {
            try {
                int id = Integer.parseInt(productIdString);
                Product product = productDao.getProductById(id);
                if (product != null) {

                    Product productForm = (Product) form;
                    productForm.setAvailableStock(product.getAvailableStock());
                    productForm.setCreatedDate(product.getCreatedDate());
                    productForm.setId(product.getId());
                    productForm.setName(product.getName());
                    productForm.setUnit(product.getUnit());

//                    request.setAttribute("product", product);
                    returnValue = SUCCESS;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(returnValue);
    }

    public boolean validateProduct(Product product, HttpServletRequest request) {
        boolean isCorrect = true;
        ActionErrors errors = new ActionErrors();
        if (product.getName() == null || "".equals(product.getName())) {
            isCorrect = false;
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("product.name.error"));
        }
        if (product.getUnit() == null || "".equals(product.getUnit())) {
            isCorrect = false;
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("product.unit.error"));
        }
        
        if(!isCorrect){
            request.setAttribute(Globals.ERROR_KEY, errors);
        }
        
        return isCorrect;

    }

    public ActionForward addProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Product product = (Product) form;
        
        boolean isValid = validateProduct(product, request);
        if(!isValid){
            return mapping.findForward(FAIL);
        }
        
        Integer returnId = productDao.addProduct(product);
        if (returnId > 0) {
            setProductsForRequest(request);
        }
        return mapping.findForward(returnId > 0 ? SUCCESS : FAIL);
    }

    public ActionForward editProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Product product = (Product) form;
        
        boolean isValid = validateProduct(product, request);
        if(!isValid){
            return mapping.findForward(FAIL);
        }
        
        boolean isSuccess = productDao.updateProduct(product);
        if (isSuccess) {
            setProductsForRequest(request);
        }
        return mapping.findForward(isSuccess ? SUCCESS : FAIL);
    }

    public ActionForward delProduct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String productIdString = request.getParameter("productId");
        if (productIdString != null && !"".equals(productIdString)) {
            int id = Integer.parseInt(productIdString);
            boolean isSuccess = productDao.deleteProduct(id);
            if (isSuccess) {
                setProductsForRequest(request);
            }
        }

        return mapping.findForward(SUCCESS);
    }

}
