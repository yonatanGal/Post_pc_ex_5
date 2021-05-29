package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        CheckBox statusCheckbox = findViewById(R.id.editCheckBox);

        Intent editIntent = getIntent();
        String stringItem = editIntent.getStringExtra("item");
        TodoItem item = holder.stringToItem(stringItem);

        data.setText(item.getData());
        timeCreated.setText("Created:" + item.getTimeCreated());
        String timeModifiedString = item.getTimeModified();
        timeModified.setText("Modified" + getTimeModifiedForUI(timeModifiedString));
        String statusString = item.getIsInProgress() ? "IN-PROGRESS" : "DONE";
        status.setText(statusString);
        statusCheckbox.setChecked(!item.getIsInProgress());

        statusCheckbox.setOnClickListener(v ->
        {
            if (statusCheckbox.isChecked()
            )
            {
                holder.markItemInProgress(item);
                statusCheckbox.setChecked(false);
                status.setText("IN-PROGRESS");
            }
            else
            {
                holder.markItemDone(item);
                statusCheckbox.setChecked(true);
                status.setText("DONE");
            }
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
                //TODO: implement
            }
        });



    }

    private String getTimeModifiedForUI(String timeStamp)
    {
        return "";
    }

}
