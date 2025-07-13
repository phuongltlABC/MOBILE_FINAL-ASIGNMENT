package com.example.myassignmentproject; // Thay đổi package của bạn

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.models.Room; // Import model Room
import com.example.adapters.RoomAdapter; // Import RoomAdapter

import java.util.List;

public class RoomListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RoomAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadRooms();
    }

    private void loadRooms() {
        List<Room> rooms = dbHelper.getAllRooms();
        if (rooms != null && !rooms.isEmpty()) {
            adapter = new RoomAdapter(rooms);
            recyclerView.setAdapter(adapter);

            // Xử lý click đơn giản (chỉ hiển thị Toast)
            adapter.setOnItemClickListener(room -> {
                Toast.makeText(RoomListActivity.this, "Clicked Room: " + room.getRoomName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, "Không tìm thấy phòng nào.", Toast.LENGTH_SHORT).show();
        }
    }
}