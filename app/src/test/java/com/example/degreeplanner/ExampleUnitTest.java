package com.example.degreeplanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    View2 view;

    @Mock
    Model model;

//    @Test
//    public void testPresenter() {
////        when(view.get_email().isEmpty());
////        when(model.login_btn(view.get_email(), view.get_pass()));
//        assertEquals(view.get_email(), null);
//
//    }
//

    @Test
    public void testPresenter2() {
        when(view.get_email()).thenReturn("sadmin@gmail.com");
        when(model.login_btn("sadmin@gmail.com", "1234567")).thenReturn(1);
//        Presenter presenter = new Presenter(model, view);
//        presenter.login("sadmin@gmail.com", "1234567");
        InOrder order = inOrder(model, view);
        order.verify(model).login_btn("sadmin@gmail.com", "1234567");
        order.verify(view).OnSuccess("Successfully logged in");
    }

}