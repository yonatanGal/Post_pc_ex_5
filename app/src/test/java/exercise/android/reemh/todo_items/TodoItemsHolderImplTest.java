package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Test;

import java.sql.Timestamp;


public class TodoItemsHolderImplTest extends TestCase {

  @Test
  public void test_when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_markingAnItemAsDone_then_ItShouldNotBeInProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    holderUnderTest.addNewInProgressItem("do shopping");
    TodoItem itemUnderTest = holderUnderTest.getCurrentItems().get(0);

    holderUnderTest.markItemDone(itemUnderTest);

    // verify
    assertFalse(holderUnderTest.getCurrentItems().get(0).getIsInProgress());
  }

  @Test
  public void test_when_callingClearList_then_ListShouldBeEmpty(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.clearList();

    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_markingDone_then_SortingChanges(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();

    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("go to the toiled");

    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    // after sorting the first item should be the 'toiled' string:
    String testString = holderUnderTest.getCurrentItems().get(0).getData();

    // verify
    assertEquals("go to the toiled", testString);
  }

  @Test
  public void test_when_DeletingItem_then_callingListShouldntHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());

    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));
    assertEquals(0,holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_addingSeveralItems_then_toDoItemsListShouldHaveTheseItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("drink water");
    holderUnderTest.addNewInProgressItem("eat some veggies");
    holderUnderTest.addNewInProgressItem("do yoga");
    assertEquals(3,holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void test_when_addingItemsAndMarkingOneDone_then_ItAppearsLast(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("drink water");
    holderUnderTest.addNewInProgressItem("eat some veggies");
    holderUnderTest.addNewInProgressItem("do yoga");

    int lastIndex = holderUnderTest.getCurrentItems().size() - 1;
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    String testString = holderUnderTest.getCurrentItems().get(lastIndex).getData();
    assertEquals(testString,"drink water");
  }

  @Test
  public void test_when_erasingItemInMiddle_then_listIsStillSorted(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("drink water");
    holderUnderTest.addNewInProgressItem("eat some veggies");
    holderUnderTest.addNewInProgressItem("do yoga");

    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(1));

    String firstItemData = holderUnderTest.getCurrentItems().get(0).getData();
    assertEquals(firstItemData,"drink water");

    String secondItemData = holderUnderTest.getCurrentItems().get(1).getData();
    assertEquals(secondItemData,"do yoga");
  }

  @Test
  public void test_when_markingAllItemsAsDone_then_listIsStillSorted(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("drink water");
    holderUnderTest.addNewInProgressItem("eat some veggies");
    holderUnderTest.addNewInProgressItem("do yoga");

    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(1));
    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(2));

    String firstItemData = holderUnderTest.getCurrentItems().get(0).getData();
    assertEquals(firstItemData,"drink water");

    String secondItemData = holderUnderTest.getCurrentItems().get(1).getData();
    assertEquals(secondItemData,"eat some veggies");

    String thirdItemData = holderUnderTest.getCurrentItems().get(2).getData();
    assertEquals(thirdItemData,"do yoga");
  }

  @Test
  public void test_when_markingDoneAndThenInProgress_then_listIsStillSorted(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("drink water");
    holderUnderTest.addNewInProgressItem("eat some veggies");
    holderUnderTest.addNewInProgressItem("do yoga");

    holderUnderTest.markItemDone(holderUnderTest.getCurrentItems().get(0));

    String testString1 = holderUnderTest.getCurrentItems().get(2).getData();
    assertEquals(testString1,"drink water");

    holderUnderTest.markItemInProgress(holderUnderTest.getCurrentItems().get(2));

    String testString2 = holderUnderTest.getCurrentItems().get(0).getData();
    assertEquals(testString2,"drink water");
  }

  @Test
  public void test_when_creatingAnItem_then_timeCreatedIsRIght(){
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("drink water");
    String curTimestamp = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
    String testTimeStamp = holderUnderTest.getCurrentItems().get(0).getTimeCreated().split(" ")[0];

    assertEquals(curTimestamp, testTimeStamp);
  }













}