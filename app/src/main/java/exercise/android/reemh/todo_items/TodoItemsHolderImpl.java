package exercise.android.reemh.todo_items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.sql.Timestamp;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  List<TodoItem> items;

  public TodoItemsHolderImpl()
  {
    this.items = new ArrayList<>();
  }

  @Override
  public List<TodoItem> getCurrentItems()
  {
    Collections.sort(this.items);
    return this.items;
  }

  @Override
  public void addNewInProgressItem(String description) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    TodoItem newInProgressItem = new TodoItem(description, timestamp.toString(), true);
    this.items.add(newInProgressItem);
    Collections.sort(this.items);

  }

  @Override
  public void markItemDone(TodoItem item) {
    for (TodoItem todoItem: this.items)
    {
      if (item.getTimeCreated().equals(todoItem.getTimeCreated()) && item.getIsInProgress())
      {
        todoItem.changeProgress();
      }
    }
    Collections.sort(this.items);

  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for (TodoItem todoItem: this.items)
    {
      if (item.getTimeCreated().equals(todoItem.getTimeCreated()) && !item.getIsInProgress())
      {
        todoItem.changeProgress();
      }
    }
    Collections.sort(this.items);

  }

  @Override
  public void deleteItem(TodoItem item) {
    items.remove(item);
    Collections.sort(this.items);
  }

  @Override
  public void clearList()
  {
    this.items.clear();
  }

}
