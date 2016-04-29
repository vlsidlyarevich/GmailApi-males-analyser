package com.mail.hunter.application.view.components;


import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

@FacesComponent("paginator")
public class Paginator extends UINamingContainer {


    public Paginator(){
    }


    public Object[] getCountPages(int allItems,int itemsOnPage){
        int count = 0;
        if(allItems%itemsOnPage==0){
            count = allItems/itemsOnPage;
        }else{
            count = allItems/itemsOnPage + 1;
        }
        Object[] arr = new Object[count];
        for(int i = 0;i<count;i++){
            arr[i] = i+1;
        }
        return arr;
    }

    public void action(AjaxBehaviorEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        MethodExpression ajaxEventListener = (MethodExpression) getAttributes().get("action");
        ajaxEventListener.invoke(context.getELContext(), new Object[] { event });
    }

}
