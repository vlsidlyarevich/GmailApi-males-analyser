package com.mail.hunter.application.view.services;

import com.mail.hunter.application.business.models.Item;
import com.mail.hunter.application.business.models.OnlinePurchase;
import com.mail.hunter.application.view.models.PurchaseModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.bean.RequestScoped;
import java.util.ArrayList;

/**
 * Created by vlad on 02.05.16.
 */

@RequestScoped
public class TreeTableService {

    public  TreeNode createNode(ArrayList<OnlinePurchase> onlinePurchases){

        TreeNode root = new DefaultTreeNode(new PurchaseModel("","","","",""));

        for(OnlinePurchase onlinePurchase : onlinePurchases){
            TreeNode node = new DefaultTreeNode(new PurchaseModel(
                    onlinePurchase.getCustomer().getDomain(),onlinePurchase.getDate(),
                    onlinePurchase.getSummary().getAmount().toString()+" "+
                    onlinePurchase.getSummary().getValue().getSymbol(),"",""),root);

            for(Item item : onlinePurchase.getItems()){
                TreeNode childNode = new DefaultTreeNode(new PurchaseModel(
                    onlinePurchase.getCustomer().getDomain(),onlinePurchase.getDate(),
                    item.getCost().getAmount().toString()+" "+
                        item.getCost().getValue().getSymbol()
                        ,item.getName(),item.getAmount()),node);
            }
        }

        return root;
    }
}
