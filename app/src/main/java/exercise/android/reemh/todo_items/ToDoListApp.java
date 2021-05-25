package exercise.android.reemh.todo_items;

import android.app.Application;

public class ToDoListApp extends Application {

    private TodoItemsHolderImpl itemsHolder;

    public TodoItemsHolderImpl getItemsHolder()
    {
        return this.itemsHolder;
    }

    //todo: implement livedata thing here

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
