package exercise.android.reemh.todo_items;

import java.io.Serializable;


public class TodoItem implements Serializable, Comparable<TodoItem> {
    private String timeModified;
    private String id;
    private String data;
    private final String timeCreated;
    private boolean isInProgress;

    public TodoItem(String id, String data, String timeCreated, String timeModified, boolean isInProgress)
  {
      this.id = id;
      this.data = data;
      this.timeCreated = timeCreated;
      this.timeModified = timeModified;
      this.isInProgress = isInProgress;
  }

    public String getId() {
        return this.id;
    }

    public String toString()
    {
        String separator = "#/#";
        String stringIsInProgress = String.valueOf(getIsInProgress());
        return this.id + separator + this.timeModified + separator + this.timeCreated + separator
                + stringIsInProgress + separator + this.data;
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

    public String getTimeModified()
    {
        return this.timeModified;
    }

    public void editTimeModified(String timeModified)
    {
        this.timeModified = timeModified;
    }

    public void editData(String newData)
    {
        if (!newData.equals(""))
        {
            this.data = newData;
        }
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


