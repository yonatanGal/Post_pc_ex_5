package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.sql.Timestamp;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  List<TodoItem> items;
  private final Context context;
  private final SharedPreferences sp;

  public TodoItemsHolderImpl(Context context)
  {
    this.items = new ArrayList<>();
    this.context = context;
    this.sp = context.getSharedPreferences("local_db_todolist", Context.MODE_PRIVATE);
  }

  @Override
  public List<TodoItem> getCurrentItems()
  {
    Collections.sort(this.items);
    return new ArrayList<>(this.items);
  }

  @Override
  public void addNewInProgressItem(String description) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    TodoItem newInProgressItem = new TodoItem(description, timestamp.toString(), true);
    this.items.add(newInProgressItem);

    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newInProgressItem.getId(), newInProgressItem.toString());
    editor.apply();
    Collections.sort(this.items);

  }

  @Override
  public void markItemDone(TodoItem item) {
    for (TodoItem todoItem: this.items)
    {
      if (item.getId().equals(todoItem.getId()) && item.getIsInProgress())
      {
        todoItem.changeProgress();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(todoItem.getId(), todoItem.itemToString());
        editor.apply();
      }
    }
    Collections.sort(this.items);

  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for (TodoItem todoItem: this.items)
    {
      if (item.getId().equals(todoItem.getId()) && !item.getIsInProgress())
      {
        todoItem.changeProgress();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(todoItem.getId(), todoItem.itemToString());
        editor.apply();
      }
    }
    Collections.sort(this.items);
  }

  @Override
  public void deleteItem(TodoItem item) {
    boolean removedSuccessfully = items.remove(item);
    if (removedSuccessfully)
    {
      SharedPreferences.Editor editor = sp.edit();
      editor.remove(item.getId());
      editor.apply();
    }
    Collections.sort(this.items);
  }

  @Override
  public void clearList()
  {
    this.items.clear();
  }

}
