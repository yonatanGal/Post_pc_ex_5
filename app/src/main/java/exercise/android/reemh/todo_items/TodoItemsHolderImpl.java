package exercise.android.reemh.todo_items;

import java.util.List;
import java.sql.Timestamp;

// TODO: implement!
public class TodoItemsHolderImpl implements TodoItemsHolder {
  

  @Override
  public List<TodoItem> getCurrentItems() { return null; }

  @Override
  public void addNewInProgressItem(String description) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    TodoItem newInProgressItem = new TodoItem(description, timestamp.toString(), true);

  }

  @Override
  public void markItemDone(TodoItem item) {
    if (item.getIsInProgress())
    {
      item.changeProgress();
    }
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    if (!item.getIsInProgress())
    {
      item.changeProgress();
    }
  }

  @Override
  public void deleteItem(TodoItem item) { }
}
