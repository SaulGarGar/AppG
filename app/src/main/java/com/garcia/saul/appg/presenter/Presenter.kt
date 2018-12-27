package com.garcia.saul.appg.presenter


open class Presenter<T :Presenter.View>{

    var view: T? = null

    open fun initialize() {}

    interface View {

    }
}
