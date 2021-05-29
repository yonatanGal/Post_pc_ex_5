package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  List<TodoItem> items;
  private final Context context;
  private final transient SharedPreferences sp;

  private transient MutableLiveData<List<TodoItem>> toDoItemsLiveDataMutable = new MutableLiveData<>();
  public transient LiveData<List<TodoItem>> toDoItemsLiveDataPublic = toDoItemsLiveDataMutable;

  public TodoItemsHolderImpl(Context context) {
    this.items = new ArrayList<>();
    this.context = context;
    this.sp = context.getSharedPreferences("local_db_todolist", Context.MODE_PRIVATE);
    initializeFromSp();
  }

  @Override
  public List<TodoItem> getCurrentItems() {
    Collections.sort(this.items);
    return new ArrayList<>(this.items);
  }

  private void initializeFromSp() {
    Set<String> keys = sp.getAll().keySet();
    for (String key : keys) {
      String toDoItemSavedAsString = sp.getString(key, null);
      TodoItem item = stringToItem(toDoItemSavedAsString);
      if (item != null) {
        items.add(item);
      }
    }
    Collections.sort(this.items);
    toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));

  }

  public TodoItem stringToItem(String string) {
    if (string == null) {
      return null;
    }
    String separator = "#/#";
    String[] components = string.split(separator, 5);
    if (components.length != 5)
    {
      return null;
    }
    String id = components[0];
    String timeModified = components[1];
    String timeCreated = components[2];
    String stringIsInProgress = components[3];
    boolean isInProgress = stringIsInProgress.equals("true");
    String data = components[4];
    return new TodoItem(id, data, timeCreated, timeModified, isInProgress);

  }

  @Override
  public void addNewInProgressItem(String description) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String newId = UUID.randomUUID().toString();
    TodoItem newInProgressItem = new TodoItem(newId, description, timestamp.toString(), timestamp.toString(), true);
    this.items.add(newInProgressItem);
    toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newInProgressItem.getId(), newInProgressItem.toString());
    editor.apply();
    Collections.sort(this.items);

  }

  @Override
  public void markItemDone(TodoItem item) {
    for (TodoItem todoItem : this.items) {
      if (item.getId().equals(todoItem.getId()) && item.getIsInProgress()) {
        todoItem.changeProgress();
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(todoItem.getId(), todoItem.toString());
        editor.apply();
      }
    }
    Collections.sort(this.items);

  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for (TodoItem todoItem : this.items) {
      if (item.getId().equals(todoItem.getId()) && !item.getIsInProgress()) {
        todoItem.changeProgress();
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(todoItem.getId(), todoItem.toString());
        editor.apply();
      }
    }
    Collections.sort(this.items);
  }

  @Override
  public void changeItemModifiedTime(TodoItem item, String modifiedTime)
  {
    for (TodoItem todoItem : this.items) {
      if (item.getId().equals(todoItem.getId()) && !item.getIsInProgress()) {
        todoItem.editTimeModified(modifiedTime);
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(todoItem.getId(), todoItem.toString());
        editor.apply();
      }
    }
    Collections.sort(this.items);
  }

  @Override
  public void changeItemData(TodoItem item, String newData)
  {
    for (TodoItem todoItem : this.items) {
      if (item.getId().equals(todoItem.getId()) && !item.getIsInProgress()) {
        todoItem.editData(newData);
        toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(todoItem.getId(), todoItem.toString());
        editor.apply();
      }
    }
    Collections.sort(this.items);
  }

  @Override
  public void deleteItem(TodoItem item) {
    boolean removedSuccessfully = items.remove(item);
    if (removedSuccessfully) {
      toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));
      SharedPreferences.Editor editor = sp.edit();
      editor.remove(item.getId());
      editor.apply();
    }
    Collections.sort(this.items);
  }


  //  @Override
//  public void clearList()
//
//  {
//    this.items.clear();
//    toDoItemsLiveDataMutable.setValue(new ArrayList<>(this.items));
//  }
}


