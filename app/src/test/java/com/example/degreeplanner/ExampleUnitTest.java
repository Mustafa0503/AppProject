package com.example.degreeplanner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    Login view;

    @Mock
    Model model;

//    @Test
//    public void testPresenter() {
//        when(view.get_email()).thenReturn("sadmin@gmail.com");
//        when(view.get_pass()).thenReturn("1234567");
//        Presenter presenter = new Presenter(model, view);
//        presenter.check_user("sadmin@gmail.com","1234567");
//        InOrder order = inOrder(model, view);
//        order.verify(model).check_user("sadmin@gmail.com", "1234567");
//
////        when(view.get_email().isEmpty());
////        when(model.login_btn(view.get_email(), view.get_pass()));
////        assertEquals(view.get_email(), null);
//
//    }
//    @Test
//    public void testModel() {
//        when(view.get_email()).thenReturn("sadmin@gmail.com");
//        when(view.get_pass()).thenReturn("1234567");
//        Model model = new Model();
//        model.check_user("sadmin@gmail.com","1234567");
//        InOrder order = inOrder(model, view);
//        order.verify(model).check_user("sadmin@gmail.com", "1234567");
//
////        when(view.get_email().isEmpty());
////        when(model.login_btn(view.get_email(), view.get_pass()));
////        assertEquals(view.get_email(), null);
//
//    }
//
//    @Test
//    public void testPresenter2() {
//        when(view.get_email().isEmpty());
//        when(model.login_btn(view.get_email(), view.get_pass()));
//          Presenter presenter = new Presenter(model, view);
//          presenter.login(vi)
//    }

}