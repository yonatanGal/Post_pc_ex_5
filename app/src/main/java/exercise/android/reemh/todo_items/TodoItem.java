package exercise.android.reemh.todo_items;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private final String data;
    private final String timeCreated;
    private boolean isInProgress;

    public TodoItem(String data, String timeCreated, boolean isInProgress)
  {
      this.data = data;
      this.timeCreated = timeCreated;
      this.isInProgress = isInProgress;
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

}
