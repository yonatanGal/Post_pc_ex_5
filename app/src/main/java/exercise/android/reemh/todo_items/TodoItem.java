package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable, Comparable<TodoItem> {
    private String timeModified;
    private String id;
    private final String data;
    private final String timeCreated;
    private boolean isInProgress;

    public TodoItem(String data, String timeCreated, boolean isInProgress)
  {
      this.data = data;
      this.timeCreated = timeCreated;
      this.timeModified = timeCreated;
      this.isInProgress = isInProgress;
  }

    public String getId() {
        return this.id;
    }

    public String itemToString()
    {
        //todo: implement me
        return "";
    }

    public String getData()
    {
      return this.data;
    }

    public String getTimeCreated()
  {
      return this.timeCreated;
  }

    public boolean getIsInProgress()
  {
      return this.isInProgress;
  }

    public void changeProgress()
  {
      this.isInProgress = !this.isInProgress;
  }

    @Override
    public int compareTo(TodoItem otherItem) {
        if (this.getIsInProgress() == otherItem.getIsInProgress())
        {
            return this.getTimeCreated().compareTo(otherItem.getTimeCreated());
        }
        if (!this.getIsInProgress())
        {
            return 1;
        }
        return -1;
    }
}
