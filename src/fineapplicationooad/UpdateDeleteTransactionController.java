/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

import java.awt.BorderLayout;

/**
 *
 * @author Hamza
 */
public class UpdateDeleteTransactionController
{

    TransactionModel model;
    UpdateDeleteTransactionView view;
    Database dao;
    SearchBarController searchBarCtrl;
    public UpdateDeleteTransactionController(TransactionModel model, UpdateDeleteTransactionView view, Database dao)
    {
        searchBarCtrl = new SearchBarController(new SearchBarView(), new SearchBarModel(), dao);
        searchBarCtrl.view.viewButton.setText("Select");
        this.model = model;
        this.view = view;
        this.dao = dao;

        view.add(searchBarCtrl.getView(), BorderLayout.WEST);
//        this.view.
    }

    public Database getDao()
    {
        return dao;
    }

    public TransactionModel getModel()
    {
        return model;
    }

    public UpdateDeleteTransactionView getView()
    {
        return view;
    }

    public void setDao(Database dao)
    {
        this.dao = dao;
    }

    public void setModel(TransactionModel model)
    {
        this.model = model;
    }

    public void setView(UpdateDeleteTransactionView view)
    {
        this.view = view;
    }

    void updateCustomersInSearchBar()
    {
        searchBarCtrl.updateCustomerList();
    }

}
