package exercise.android.reemh.todo_items;

import android.app.Application;

import java.io.Serializable;

public class ToDoListApp extends Application implements Serializable {

    private TodoItemsHolderImpl itemsHolder;

    public TodoItemsHolderImpl getItemsHolder()
    {
        return this.itemsHolder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.itemsHolder = new TodoItemsHolderImpl(this);
    }

    private static ToDoListApp instance = null;

    public static ToDoListApp getInstance()
    {
        return instance;
    }
}
