package com.glen.filetest.presenter;

import com.glen.filetest.view.IMainView;

public class MainPresenter implements IMainPresenter{

    IMainView iMainView;


    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }
}
