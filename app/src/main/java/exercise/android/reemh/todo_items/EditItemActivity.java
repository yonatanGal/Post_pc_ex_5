package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;

public class EditItemActivity extends AppCompatActivity {
    public TodoItemsHolderImpl holder = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item);

        if (holder == null)
        {
            holder = ToDoListApp.getInstance().getItemsHolder();
        }

        EditText data = findViewById(R.id.editDataText);
        TextView timeCreated = findViewById(R.id.editTimeCreated);
        TextView timeModified = findViewById(R.id.editTimeModified);
        TextView status = findViewById(R.id.editStatus);
        Button statusButton = findViewById(R.id.editCheckBox);
        Button finishButton = findViewById(R.id.finishButton);

        Intent editIntent = getIntent();
        String stringItem = editIntent.getStringExtra("item");
        TodoItem item = holder.stringToItem(stringItem);

        data.setText(item.getData());
        timeCreated.setText("Time Created: " + item.getTimeCreated().substring(0, 19));
        String timeModifiedString = item.getTimeModified();
        timeModified.setText("Time Modified: " + getTimeModifiedForUI(timeModifiedString));
        String statusString = item.getIsInProgress() ? "IN-PROGRESS" : "DONE";
        status.setText(statusString);

        statusButton.setOnClickListener(v ->
        {
            if (item.getIsInProgress())
            {
                holder.markItemDone(item);
                item.changeProgress();
                status.setText("DONE");
            }
            else
            {
                holder.markItemInProgress(item);
                item.changeProgress();
                status.setText("IN-PROGRESS");
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String newModifiedTime = getTimeModifiedForUI(timestamp.toString());
            timeModified.setText("Time Modified: " + newModifiedTime);
            holder.changeItemModifiedTime(item, timestamp.toString());
        }

        );

        data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newData = s.toString();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String newModifiedTime = getTimeModifiedForUI(timestamp.toString());
                timeModified.setText("Time Modified: " + newModifiedTime);
                holder.changeItemModifiedTime(item, timestamp.toString());
                holder.changeItemData(item, newData);
                item.editData(newData);
            }
        });

        finishButton.setOnClickListener(v ->
        {
            Intent goBackIntent = new Intent(v.getContext(), MainActivity.class);
            v.getContext().startActivity(goBackIntent);
        });



    }

    private String getTimeModifiedForUI(String timeStamp)
    {
        Timestamp curTimestamp = new Timestamp(System.currentTimeMillis());
        String stringCurTime = curTimestamp.toString();
        Timestamp modified = Timestamp.valueOf(timeStamp);
        long diff = curTimestamp.getTime() - modified.getTime();
        if (diff > 0)
        {
            diff = (long) (diff / 60000.0);
        }
        if (diff < 60)
        {
            return diff + " minutes ago";
        }
        else if (timeStamp.substring(0, 10).equals(stringCurTime.substring(0, 10)))
        {
            return "Today at " +stringCurTime.substring(11, 16);
        }
        else return stringCurTime.substring(0, 10) + "at " + stringCurTime.substring(11,16);
    }

}
