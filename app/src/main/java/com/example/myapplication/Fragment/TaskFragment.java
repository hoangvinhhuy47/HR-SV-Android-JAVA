
package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Adpter.TaskAdapter;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Interface.UpdateTask;
import com.example.myapplication.Model.Task;
import com.example.myapplication.Response.TaskReponse;
import com.example.myapplication.R;
import com.example.myapplication.Request.TaskRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskFragment extends Fragment implements UpdateTask {
    TaskAdapter taskAdapter;
    private RecyclerView recyclerView;
    RelativeLayout main;
    private int pagenumber = 1;
    Boolean isScrolling = false;
    LinearLayoutManager linearLayoutManager;
    List<Task> taskList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_task, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setVerticalScrollBarEnabled(true);
        initTaskAdapter();
        //căng size của recycler
        //tùy chỉnh cố slg item trong rycle
        taskAdapter = new TaskAdapter(taskList,getActivity(),TaskFragment.this);

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initTaskAdapter() {
        linearLayoutManager = new LinearLayoutManager(getContext());

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUserID(Gobal.getUser().getUserID());
        taskRequest.setPageNumber(pagenumber);
        GetData(taskRequest);


    }

    public void loadMore(int pagenumber) {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUserID(Gobal.getUser().getUserID());
        taskRequest.setPageNumber(pagenumber);
        GetMoreData(taskRequest);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void GetData(TaskRequest taskRequest) {
        Call<TaskReponse> taskReponseCall = APIClient.getCongViecSerVice().GetData(Gobal.getGuiID(), taskRequest);
        taskReponseCall.enqueue(new Callback<TaskReponse>() {
            @Override
            public void onResponse(Call<TaskReponse> call, Response<TaskReponse> response) {
                if (response.isSuccessful()) {
                    try {
                        TaskReponse taskReponse = response.body();
                        if (taskReponse.getStatusID() == 1) {
                            Gobal.setTotalpage(taskReponse.getTotalPage());
                            taskList.removeAll(taskList);
                            taskList.addAll(taskReponse.getData());
                            taskAdapter = new TaskAdapter(taskList,getActivity(),TaskFragment.this);
                            recyclerView.setAdapter(taskAdapter);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                        if (!isScrolling) {
                                            if (linearLayoutManager != null || linearLayoutManager.findLastCompletelyVisibleItemPosition() == taskList.size() - 1) {
                                                if (pagenumber < Gobal.getTotalpage()) {
                                                    pagenumber = pagenumber + 1;
                                                    loadMore(pagenumber);
                                                } else {
                                                    isScrolling = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "Không Có Dữ Liệu", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception exception) {
                        Toast.makeText(getActivity(), "Không Có Dữ Liệu", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskReponse> call, Throwable t) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.alert_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button accept = dialog.findViewById(R.id.accept);
                ImageView exit = dialog.findViewById(R.id.exit);
                TextView ttvTitle = dialog.findViewById(R.id.ttvTitle);
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ttvTitle.setText("Lỗi kết Nối, Vui Lòng Thử Lại Sau");
            }
        });
    }

    public void GetMoreData(TaskRequest taskRequest) {
        Call<TaskReponse> taskReponseCall = APIClient.getCongViecSerVice().GetData(Gobal.getGuiID(), taskRequest);
        taskReponseCall.enqueue(new Callback<TaskReponse>() {
            @Override
            public void onResponse(Call<TaskReponse> call, Response<TaskReponse> response) {
                if (response.isSuccessful()) {
//                    showLoading();
                    TaskReponse taskReponse = response.body();
                    if (taskReponse.getStatusID() == 1 && taskReponse.getData().size() != 0) {
                        try {
                            taskAdapter.Loadmore(taskReponse.getData());
                            taskList.addAll(taskReponse.getData());
                        }
                        catch (Exception exception){
                            Toast.makeText(getActivity(),""+exception.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        isScrolling = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskReponse> call, Throwable t) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.alert_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button accept = dialog.findViewById(R.id.accept);
                ImageView exit = dialog.findViewById(R.id.exit);
                TextView ttvTitle = dialog.findViewById(R.id.ttvTitle);
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ttvTitle.setText("Lỗi kết Nối,Vui Lòng Thử Lại Sau");
            }
        });
    }

    public void showLoading() {
        if (getActivity() == null)
            return;
        getActivity().findViewById(R.id.loader).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        if (getActivity() == null)
            return;

        getActivity().findViewById(R.id.loader).setVisibility(View.GONE);
    }

    @Override
    public void update(int position, List<Task> lst) {
        pagenumber=1;
        taskAdapter = new TaskAdapter(taskList,getActivity(),TaskFragment.this);
        taskAdapter.notifyDataSetChanged();
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUserID(Gobal.getUser().getUserID());
        taskRequest.setPageNumber(pagenumber);
        GetData(taskRequest);
    }
}

