/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fineapplicationooad;

/**
 *
 * @author Hamza
 */
public class UpdateDeleteTransactionController
{
    TransactionModel model;
    UpdateDeleteTransactionView view;
    Database dao;

    public UpdateDeleteTransactionController(TransactionModel model, UpdateDeleteTransactionView view, Database dao)
    {
        this.model = model;
        this.view = view;
        this.dao = dao;
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
    
    
    
}
