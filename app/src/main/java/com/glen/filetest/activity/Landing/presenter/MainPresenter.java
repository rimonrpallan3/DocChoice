package com.glen.filetest.activity.Landing.presenter;

import com.glen.filetest.activity.Landing.view.IMainView;

public class MainPresenter implements IMainPresenter{

    IMainView iMainView;


    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }
}
