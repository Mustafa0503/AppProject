//package com.example.degreeplanner;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InOrder;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.inOrder;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(MockitoJUnitRunner.class)
//public class ExampleUnitTest {
//    @Mock
//    Contract.Login view;
//
//    @Mock
//    Contract.Model model;
//
//    @Mock
//    Model.UserCallBack userCallBack;
//
//
//    @Test
//    public void testPresenter() {
//        assertEquals(view.get_email(), null);
//
//    }
//
//
////    @Test
////    public void testPresenter2() {
////    //    when(model.all_users("admin10@gmail.com","1234567", userCallBack)).thenReturn(null);
////        when(userCallBack.check_user(1)).thenReturn(1);
////        Presenter presenter = new Presenter(model, view);
////        presenter.all_u("admin10@gmail.com","1234567");
////        verify(view).NO();
////
////    }
//
////    @Test
////    public void testPresenter(){
////        when(model.all_users("admin10@");)
////    }
////
////    @Test
////    public void testInfo(){
////        when(view.get_email()).thenReturn("admin10@gmail.com");
////        when(view.get_pass()).thenReturn("1234567");
////        verify(view).Admin();
////    }
////
//////    @Test
////    public void testPresenter3() {
////        when(view.get_email()).thenReturn("");
////        when(model.login_btn("", "1234567")).thenReturn(-1);
////        Presenter presenter = new Presenter(model, view);
////        presenter.error_msg("", "1234567");
////        InOrder order = inOrder(model, view);
////        order.verify(model).login_btn("", "1234567");
////        order.verify(view).OnSuccess("Successfully logged in");
////    }
////
////    @Test
////    public void testPresenter4() {
////        when(view.get_email()).thenReturn("sadmin@gmail.com");
////        when(model.login_btn("sadmin@gmail.com", "1234567")).thenReturn(1);
////        Presenter presenter = new Presenter(model, view);
////        presenter.login("sadmin@gmail.com", "1234567");
////        InOrder order = inOrder(model, view);
////        order.verify(model).login_btn("sadmin@gmail.com", "1234567");
////        order.verify(view).OnSuccess("Successfully logged in");
////    }
////
////    @Test
////    public void testPresenter6() {
////        when(view.get_email()).thenReturn("sadmin@gmail.com");
////        when(model.login_btn("sadmin@gmail.com", "1234567")).thenReturn(1);
////        Presenter presenter = new Presenter(model, view);
////        presenter.login("sadmin@gmail.com", "1234567");
////        InOrder order = inOrder(model, view);
////        order.verify(model).login_btn("sadmin@gmail.com", "1234567");
////        order.verify(view).OnSuccess("Successfully logged in");
////    }
//
//}