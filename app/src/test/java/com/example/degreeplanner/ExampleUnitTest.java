//package com.example.degreeplanner;
//
//import static com.google.common.base.CharMatcher.any;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InOrder;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.runner.Request.method;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.inOrder;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.only;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.google.firebase.firestore.auth.User;
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(MockitoJUnitRunner.class)
//
//public class ExampleUnitTest {
//    @Mock
//    Contract.Login view;
//
//    @Mock
//    Contract.Model model;
//    Presenter presenter;
//
//    @Mock
//    Model.UserCallBack userCallBack;
//
//
//
//    @Before
//    public void setUp() throws Exception{
//        presenter = new Presenter(model, view);
//    }
//    @Test
//    public void testPresenter() {
//        assertEquals(view.get_email(), null);
//
//    }
//
//    @Test
//    public void showIsEmpty()throws Exception{
//        when(view.get_email()).thenReturn("");
//        presenter.onClick();
//        verify(view).showEmailError(R.string.email_error);
//    }
//
//    @Test
//
//    public void IsPassEmpty() throws Exception{
//        when(view.get_email()).thenReturn("admin10@gmail.com");
//        when(view.get_pass()).thenReturn("");
//        presenter.onClick();
//        verify(view).showPassError(R.string.pass_error);
//    }
//
//    @Test
//
//    public void lenPassEmpty() throws Exception{
//        when(view.get_email()).thenReturn("admin10@gmail.com");
//        when(view.get_pass()).thenReturn("1234");
//        presenter.onClick();
//        verify(view).lenPassError(R.string.must_be);
//    }
//
////    @Test
////    public void openAdmin() throws Exception{
////        when(view.get_email()).thenReturn("admin10@gmail.com");
////        when(view.get_pass()).thenReturn("1234567");
////        when(model.all_users("admin10@gmail.com", "1234567", userCallBack)).thenReturn(1);
////        when(userCallBack.check_user(1)).thenReturn(1);
////        presenter.onClick();
////        verify(view).Admin();
////    }
////
////    @Test
////    public void openStudent() throws Exception{
////        when(view.get_email()).thenReturn("mstudent@gmail.com");
////        when(view.get_pass()).thenReturn("1234567");
////        when(model.all_users("mstudent@gmail.com", "1234567", userCallBack)).thenReturn(1);
////        when(userCallBack.check_user(1)).thenReturn(2);
////        presenter.onClick();
////        verify(view).Student();
////    }
////
////    @Test
////    public void openNo() throws Exception{
////        when(view.get_email()).thenReturn("mdmdek@gmail.com");
////        when(view.get_pass()).thenReturn("1234567");
////        when(model.all_users("mdmdek@gmail.com", "1234567", userCallBack)).thenReturn(1);
////        when(userCallBack.check_user(1)).thenReturn(0);
////        presenter.onClick();
////        verify(view).Student();
////    }
//
//
////    @Test
////    public void oenAdmin(){
////        Contract.Model spyTemp = Mockito.spy(model);
////        boolean expected = true;
////        Mockito.doReturn(expected).when(spyTemp).all_users(view.get_email(), view.get_email());
////
////        // when
////        int actual = spyTemp.check_user(1);
////
////        // then (faster readable)
////        Mockito.verify(spyTemp, times(1)).check_user(1);
////        when(model.all_users("admin10@gmail.com", "1234567", userCallBack)).thenReturn(userCallBack.check_user(1));
////        presenter.onClick();
////        verify(view).Admin();
////    }
////    @Test
////    public void openAdmin() throws Exception{
////        when(view.get_email()).thenReturn("admin10@gmail.com");
////        when(view.get_pass()).thenReturn("1234567");
////        when(view.Admin()).thenReturn(1);
//////        when(userCallBack.check_user(1)).thenReturn(1);
////        when(model.all_users("admin10@gmail.com", "1234567", userCallBack)).thenCallRealMethod();
////        presenter.onClick();
////        verify(view).Admin();
////    }
//
//    @Test
//    public void openAdmin() throws Exception{
//        when(view.get_email()).thenReturn("admin10@gmail.com");
//        when(view.get_pass()).thenReturn("1234567");
//        when(model.login_btn("admin10@gmail.com","1234567")).thenReturn(1);
//        presenter.onClick();
//        verify(view).Admin();
//    }
//    @Test
//    public void openStudent() throws Exception{
//        when(view.get_email()).thenReturn("mstudent@gmail.com");
//        when(view.get_pass()).thenReturn("1234567");
//        when(model.login_btn("mstudent@gmail.com","1234567")).thenReturn(2);
//        presenter.onClick();
//        verify(view).Student();
//    }
//
//}