package com.github.vlsidlyarevich.GMailQuickStart.Controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "CollapseController")
@SessionScoped
public class CollapsiblePanelBean {

    private boolean collapsed = false;

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

}