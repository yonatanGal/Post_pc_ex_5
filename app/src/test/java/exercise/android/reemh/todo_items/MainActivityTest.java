package exercise.android.reemh.todo_items;

import android.os.Looper;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MainActivityTest extends TestCase {
  @Test
  public void when_activityIsLaunched_then_theEditTextStartsEmpty() {
    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
    EditText editText = activity.findViewById(R.id.editTextInsertTask);
    String userInput = editText.getText().toString();
    assertTrue(userInput.isEmpty());
  }

  @Test
  public void when_activityIsLaunched_then_recyclerViewShowsZeroItems() {
    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
    RecyclerView recyclerView = activity.findViewById(R.id.recyclerTodoItemsList);
    RecyclerView.Adapter adapter = recyclerView.getAdapter();
    assertNotNull(adapter);
    assertEquals(0, adapter.getItemCount());
  }

  @Test
  public void when_userPutInputAndClicksButton_then_newTodoViewShouldBeShown() {
    // setup
    MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
    EditText editText = activity.findViewById(R.id.editTextInsertTask);
    View fab = activity.findViewById(R.id.buttonCreateTodoItem);
    RecyclerView recyclerView = activity.findViewById(R.id.recyclerTodoItemsList);

    // test
    editText.setText("Call my grandma today at 18:00");
    fab.performClick();
    Shadows.shadowOf(Looper.getMainLooper()).idle(); // wait until adapter is done

    // verify:

    // 1. verify that adapter says there should be 1 item showing
    RecyclerView.Adapter adapter = recyclerView.getAdapter();
    assertNotNull(adapter);
    assertEquals(1, adapter.getItemCount());

    // 2. verify that the shown view has a checkbox being not-checked and has a TextView showing the description
    View viewInRecycler = recyclerView.findViewHolderForAdapterPosition(0).itemView;
    // TODO: implement.
    //  use `viewInRecycler.findViewById(...)` to find the checkbox and the description subviews,
    //  and make sure they are set to the correct output
  }

  @Test
  public void when_userPutInputAndClicksButton_then_inputShouldBeErasedFromEditText() {
    //    TODO: implement
  }
}
