package com.github.vlsidlyarevich.GMailQuickStart.BusinessLogic.Controllers;


import javax.faces.bean.ManagedBean;

@ManagedBean
public class AvailableStoresController {

    private boolean steamCheck;
    private boolean aliExpressCheck;
    private boolean originCheck;

    public AvailableStoresController(){
        steamCheck = false;
        aliExpressCheck = false;
        originCheck = false;
    }

    public boolean isSteamCheck() {
        return steamCheck;
    }

    public void setSteamCheck(boolean steamCheck) {
        this.steamCheck = steamCheck;
    }

    public boolean isAliExpressCheck() {
        return aliExpressCheck;
    }

    public void setAliExpressCheck(boolean aliExpressCheck) {
        this.aliExpressCheck = aliExpressCheck;
    }

    public boolean isOriginCheck() {
        return originCheck;
    }

    public void setOriginCheck(boolean originCheck) {
        this.originCheck = originCheck;
    }
}
