package com.vanba.stock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.MappingDispatchAction;

import com.vanba.stock.dao.AppUserDao;
import com.vanba.stock.dao.impl.AppUserDaoImpl;
import com.vanba.stock.model.AppUser;

public class LoginController extends MappingDispatchAction {

    private final static String SUCCESS = "success";
    private final static String FAIL = "failure";
    private AppUserDao personDao;

    public LoginController() {
        personDao = AppUserDaoImpl.getInstance();
    }

    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        AppUser userInfo = (AppUser) form;
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("AppUserTocken") != null) {
                return mapping.findForward(SUCCESS);
            }
        }else {
            session = request.getSession(true);
        }
        boolean isSuccess = personDao.checkLogin(userInfo);
        String returnValue = isSuccess ? SUCCESS : FAIL;
        if (isSuccess) {
            session.setAttribute("AppUserTocken", userInfo.getName() + userInfo.getPass());
        } else {
            session.removeAttribute("AppUserTocken");
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalidCredentials"));
            request.setAttribute(Globals.ERROR_KEY, errors);
        }
        return mapping.findForward(returnValue);

    }

    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("AppUserTocken");
        return mapping.findForward(SUCCESS);
    }

}
