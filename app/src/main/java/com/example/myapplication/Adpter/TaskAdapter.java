package com.example.myapplication.Adpter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Fragment.RecyclerEntity;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Interface.UpdateTask;
import com.example.myapplication.Model.Task;
import com.example.myapplication.Model.DataTaskEmployee;
import com.example.myapplication.R;
import com.example.myapplication.Response.ApprovalRespone;
import com.example.myapplication.Response.DenyReponse;
import com.example.myapplication.Response.UpdatePercentRespone;
import com.example.myapplication.Response.UpdateStarReponse;
import com.example.myapplication.Request.ApprovalRequest;
import com.example.myapplication.Request.DenyRequest;
import com.example.myapplication.Request.UpdatePercentRequest;
import com.example.myapplication.Request.UpdateStarRequest;
import com.example.myapplication.Utils.GobalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.R.drawable.star;
import static com.example.myapplication.R.drawable.star_check;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private Context context;
    List<RecyclerEntity> tasks;
    private List<Task> taskslist = new ArrayList<>();
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    UpdateTask updateTask;

    public TaskAdapter(List<Task> taskslist, Context context, UpdateTask updateTask) {
        this.taskslist.addAll(taskslist);
        this.context = context;
        this.updateTask = updateTask;
    }

    public void Loadmore(List<Task> lst) {
        taskslist.addAll(lst);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_itemtask, parent, false);
//
//        return new MyViewHolder(itemView);

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemtask, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            Task task = taskslist.get(position);
            Picasso.with(context);
            holder.txtTaksname.setText(task.getTaskName());
            holder.txtFinalPercent.setText(String.valueOf(task.getFinalPercent()).concat("%"));
            holder.txtTaskStatus.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskStatus(task.getTaskStatus()));
            holder.txtToday.setText(GobalUtils.convertDateShowScreen1(GobalUtils.convertStringToDate(task.getToDate())));
            holder.txtFromday.setText(GobalUtils.convertDateShowScreen1(GobalUtils.convertStringToDate(task.getFromDate())));
            holder.txtPriority.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskPriority(task.getPriority()));
            SetImgStar(task.getIsStar(), holder.ImgStar);
            if (position % 2 == 0) {
                holder.container.setCardBackgroundColor(Color.parseColor("#EBF4EB"));
            } else {
                holder.container.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            holder.ImgStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.process_star.setVisibility(View.VISIBLE);
                    CountDownTimer countDownTimer = new CountDownTimer(800, 100) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            Gobal.setTaskEmployeeID(String.valueOf(task.getTaskEmployeeID()));
                            UpdateStarRequest updateStarRequest = new UpdateStarRequest();
                            updateStarRequest.setTaskEmployeeID(task.getTaskEmployeeID());
                            UpdateStar(updateStarRequest);
                            task.setIsStar(!task.getIsStar());
                            SetImgStar(task.getIsStar(), holder.ImgStar);
                            holder.process_star.setVisibility(View.GONE);
                        }
                    }.start();

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog1 = new Dialog(context);
                    dialog1.setContentView(R.layout.dialog_infomationtask);
                    dialog1.getWindow().setGravity(Gravity.CENTER);
                    dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dialog1.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog1.show();
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    TextView inftaskname = dialog1.findViewById(R.id.inftaskname);
                    TextView fromdaytaskinf = dialog1.findViewById(R.id.fromdaytaskinf);
                    TextView todaytaskinf = dialog1.findViewById(R.id.todaytaskinf);
                    TextView prioritytaksinf = dialog1.findViewById(R.id.prioritytaksinf);
                    TextView stttaskinf = dialog1.findViewById(R.id.stttaskinf);
                    TextView finaltaskinf = dialog1.findViewById(R.id.finaltaskinf);
                    ImageView closeinftask = dialog1.findViewById(R.id.closeinftask);
                    Button imfexitinfo = dialog1.findViewById(R.id.imfexitinfo);
                    inftaskname.setText(task.getTaskName());
                    fromdaytaskinf.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(task.getFromDate())));
                    todaytaskinf.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(task.getToDate())));
                    finaltaskinf.setText(String.valueOf(task.getFinalPercent()).concat("%"));
                    prioritytaksinf.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskPriority(task.getPriority()));
                    stttaskinf.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskStatus(task.getTaskStatus()));
                    imfexitinfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog1.dismiss();
                        }
                    });
                    closeinftask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog1.dismiss();
                        }
                    });
                }
            });
            holder.ImgMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, holder.ImgMenu);
                    popupMenu.inflate(R.menu.optionmenu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {

                                case R.id.accpet:
                                    if (task.getTaskStatus() == 1) {
                                        ApprovalRequest approvalRequest = new ApprovalRequest();
                                        approvalRequest.setTaskEmployeeID(task.getTaskEmployeeID());
                                        Accept(approvalRequest);
                                        task.setTaskStatus(2);
                                        return true;
                                    }
                                    Dialog dialogerro = new Dialog(context);
                                    dialogerro.setContentView(R.layout.alert_dialog);
                                    dialogerro.getWindow().setGravity(Gravity.CENTER);
                                    dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                    dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                                    ImageView exit = dialogerro.findViewById(R.id.exit);
                                    Button accept = dialogerro.findViewById(R.id.accept);
                                    dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialogerro.show();
                                    ttvTitle.setText("Công Việc Đã Được Xác Nhận Yêu Cầu");
                                    exit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogerro.dismiss();
                                        }
                                    });
                                    accept.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogerro.dismiss();
                                        }
                                    });
                                    return false;
                                case R.id.refuse:
                                    if (task.getTaskStatus() == 1) {
                                        DenyRequest denyRequest = new DenyRequest();
                                        denyRequest.setTaskEmployeeID(task.getTaskEmployeeID());
                                        Deny(denyRequest);
                                        task.setTaskStatus(2);
                                        return true;
                                    }
                                    Dialog dialogerroo = new Dialog(context);
                                    dialogerroo.setContentView(R.layout.alert_dialog);
                                    dialogerroo.getWindow().setGravity(Gravity.CENTER);
                                    dialogerroo.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    dialogerroo.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                    dialogerroo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    TextView ttvinfo = dialogerroo.findViewById(R.id.ttvTitle);
                                    ImageView exiterro = dialogerroo.findViewById(R.id.exit);
                                    Button accepterro = dialogerroo.findViewById(R.id.accept);
                                    dialogerroo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialogerroo.show();
                                    ttvinfo.setText("Công Việc Đã Được Xác Nhận Yêu Cầu");
                                    exiterro.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogerroo.dismiss();
                                        }
                                    });
                                    accepterro.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogerroo.dismiss();
                                        }
                                    });
                                    return false;
                                case R.id.UpdatePercent:
                                    Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.dialog_notetask);
                                    dialog.getWindow().setGravity(Gravity.CENTER);
                                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    TextView Tasknamedialog = dialog.findViewById(R.id.Tasknamedialog);
                                    Button btnAccept = dialog.findViewById(R.id.accept);
                                    Button btnClose = dialog.findViewById(R.id.close);
                                    ImageView imgexit = dialog.findViewById(R.id.imgexit);
                                    EditText txtdetailtask = dialog.findViewById(R.id.detailtask);
                                    EditText txtfinalPercent = dialog.findViewById(R.id.finalPercent);
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog.show();
                                    Tasknamedialog.setText(holder.txtTaksname.getText().toString());
                                    imgexit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });
                                    btnClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                    btnAccept.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (txtdetailtask.length() == 0 || txtfinalPercent.length() == 0) {
                                                Dialog warring = new Dialog(context);
                                                warring.setContentView(R.layout.alert_dialog);
                                                warring.getWindow().setGravity(Gravity.CENTER);
                                                warring.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                warring.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                                warring.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                warring.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                TextView ttvTitle = warring.findViewById(R.id.ttvTitle);
                                                ImageView exit = warring.findViewById(R.id.exit);
                                                TextView accept = warring.findViewById(R.id.accept);
                                                warring.show();
                                                ttvTitle.setText("Không Được Để Trống");
                                                exit.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        warring.dismiss();
                                                    }
                                                });
                                                accept.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        warring.dismiss();
                                                    }
                                                });
                                            } else {
                                                UpdatePercentRequest updatePercentRequest = new UpdatePercentRequest();
                                                DataTaskEmployee taskEmployee = new DataTaskEmployee();
                                                taskEmployee.setFinalPercent(Integer.parseInt(txtfinalPercent.getText().toString()));
                                                if (taskEmployee.getFinalPercent() < 0 || taskEmployee.getFinalPercent() > 100) {
                                                    Dialog dialogerro = new Dialog(context);
                                                    dialogerro.getWindow().setGravity(Gravity.CENTER);
                                                    dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                    dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                                    dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                    dialogerro.setContentView(R.layout.alert_dialog);
                                                    TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                                                    ImageView exit = dialogerro.findViewById(R.id.exit);
                                                    Button accept = dialogerro.findViewById(R.id.accept);
                                                    dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                    dialogerro.show();
                                                    ttvTitle.setText("Phải Nhập Từ 0 -> 100%");
                                                    exit.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            dialogerro.dismiss();
                                                        }
                                                    });
                                                    accept.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            dialogerro.dismiss();
                                                        }
                                                    });
                                                } else {
                                                    taskEmployee.setNote(txtdetailtask.getText().toString());

                                                    taskEmployee.setTaskEmployeeID(task.getTaskEmployeeID());
                                                    updatePercentRequest.setTaskEmployeeID(taskEmployee);
                                                    UpdatePercent(updatePercentRequest);
                                                    dialog.cancel();

                                                }
                                            }
                                        }
                                    });

                                    return true;
                                case R.id.detail:
                                    Dialog dialoginfo = new Dialog(context);
                                    dialoginfo.setContentView(R.layout.dialog_infomationtask);
                                    dialoginfo.getWindow().setGravity(Gravity.CENTER);
                                    dialoginfo.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    dialoginfo.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                                    dialoginfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialoginfo.show();
                                    TextView inftaskname = dialoginfo.findViewById(R.id.inftaskname);
                                    TextView fromdaytaskinf = dialoginfo.findViewById(R.id.fromdaytaskinf);
                                    TextView todaytaskinf = dialoginfo.findViewById(R.id.todaytaskinf);
                                    TextView prioritytaksinf = dialoginfo.findViewById(R.id.prioritytaksinf);
                                    TextView stttaskinf = dialoginfo.findViewById(R.id.stttaskinf);
                                    TextView finaltaskinf = dialoginfo.findViewById(R.id.finaltaskinf);
                                    ImageView closeinftask = dialoginfo.findViewById(R.id.closeinftask);
                                    Button imfexitinfo = dialoginfo.findViewById(R.id.imfexitinfo);
                                    inftaskname.setText(task.getTaskName());
                                    fromdaytaskinf.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(task.getFromDate())));
                                    todaytaskinf.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(task.getToDate())));
                                    finaltaskinf.setText(String.valueOf(task.getFinalPercent()).concat("%"));
                                    prioritytaksinf.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskPriority(task.getPriority()));
                                    stttaskinf.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskStatus(task.getTaskStatus()));
                                    closeinftask.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialoginfo.dismiss();
                                        }
                                    });
                                    imfexitinfo.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialoginfo.dismiss();
                                        }
                                    });
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popupMenu.show();
                }
            });
            holder.txtTaksname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog1 = new Dialog(context);
                    dialog1.setContentView(R.layout.dialog_infomationtask);
                    dialog1.getWindow().setGravity(Gravity.CENTER);
                    dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dialog1.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog1.show();
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    TextView inftaskname = dialog1.findViewById(R.id.inftaskname);
                    TextView fromdaytaskinf = dialog1.findViewById(R.id.fromdaytaskinf);
                    TextView todaytaskinf = dialog1.findViewById(R.id.todaytaskinf);
                    TextView prioritytaksinf = dialog1.findViewById(R.id.prioritytaksinf);
                    TextView stttaskinf = dialog1.findViewById(R.id.stttaskinf);
                    TextView finaltaskinf = dialog1.findViewById(R.id.finaltaskinf);
                    ImageView closeinftask = dialog1.findViewById(R.id.closeinftask);
                    inftaskname.setText(task.getTaskName());
                    fromdaytaskinf.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(task.getFromDate())));
                    todaytaskinf.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(task.getToDate())));
                    finaltaskinf.setText(String.valueOf(task.getFinalPercent()).concat("%"));
                    prioritytaksinf.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskPriority(task.getPriority()));
                    stttaskinf.setText(com.example.myapplication.Utils.GobalUtils.ConverTaskStatus(task.getTaskStatus()));
                    Button imfexitinfo = dialog1.findViewById(R.id.imfexitinfo);
                    imfexitinfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog1.dismiss();
                        }
                    });
                    closeinftask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog1.dismiss();
                        }
                    });

                }
            });

        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }

    }


    public void Accept(ApprovalRequest approvalRequest) {
        Call<ApprovalRespone> approvalResponeCall = APIClient.getApprovalService().GetStar(Gobal.getGuiID(), approvalRequest);
        approvalResponeCall.enqueue(new Callback<ApprovalRespone>() {
            @Override
            public void onResponse(Call<ApprovalRespone> call, Response<ApprovalRespone> response) {


                if (response.isSuccessful()) {
                    Dialog dialogerro = new Dialog(context);
                    dialogerro.setContentView(R.layout.alert_dialog);
                    TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                    ImageView exit = dialogerro.findViewById(R.id.exit);
                    Button accept = dialogerro.findViewById(R.id.accept);
                    dialogerro.getWindow().setGravity(Gravity.CENTER);
                    dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                    dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialogerro.show();
                    ttvTitle.setText("Chấp Nhận Thành Công Đợi Phản Hồi");
                    exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogerro.dismiss();
                        }
                    });
                    accept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogerro.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ApprovalRespone> call, Throwable t) {
                Dialog dialogerro = new Dialog(context);
                dialogerro.setContentView(R.layout.alert_dialog);
                TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                ImageView exit = dialogerro.findViewById(R.id.exit);
                Button accept = dialogerro.findViewById(R.id.accept);
                dialogerro.getWindow().setGravity(Gravity.CENTER);
                dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogerro.show();
                ttvTitle.setText("Mất Kết Nối");
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogerro.dismiss();
                    }
                });
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogerro.dismiss();
                    }
                });
            }
        });
    }

    public void Deny(DenyRequest denyRequest) {
        Call<DenyReponse> denyReponseCall = APIClient.DenyService().Deny(Gobal.getGuiID(), denyRequest);
        denyReponseCall.enqueue(new Callback<DenyReponse>() {
            @Override
            public void onResponse(Call<DenyReponse> call, Response<DenyReponse> response) {

                if (response.isSuccessful()) {
                    Dialog dialogerro = new Dialog(context);
                    dialogerro.setContentView(R.layout.alert_dialog);
                    TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                    ImageView exit = dialogerro.findViewById(R.id.exit);
                    Button accept = dialogerro.findViewById(R.id.accept);
                    dialogerro.getWindow().setGravity(Gravity.CENTER);
                    dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                    dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialogerro.show();
                    ttvTitle.setText("Từ Chối Thành Công , Đợi Phản Hồi");
                    exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogerro.dismiss();
                        }
                    });
                    accept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialogerro.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DenyReponse> call, Throwable t) {
                Dialog dialogerro = new Dialog(context);
                dialogerro.setContentView(R.layout.alert_dialog);
                TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                ImageView exit = dialogerro.findViewById(R.id.exit);
                Button accept = dialogerro.findViewById(R.id.accept);
                dialogerro.getWindow().setGravity(Gravity.CENTER);
                dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogerro.show();
                ttvTitle.setText("Mất Kết Nối");
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogerro.dismiss();
                    }
                });
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogerro.dismiss();
                    }
                });
            }
        });
    }

    public void UpdateStar(UpdateStarRequest updateStarRequest) {
        Call<UpdateStarReponse> updateStarReponseCall = APIClient.updateStarService().UpdateStar(Gobal.getGuiID(), updateStarRequest);
        updateStarReponseCall.enqueue(new Callback<UpdateStarReponse>() {
            @Override
            public void onResponse(Call<UpdateStarReponse> call, Response<UpdateStarReponse> response) {
                if (response.isSuccessful()) {
                    UpdateStarReponse updateStarReponse = response.body();
                    if (updateStarReponse.getStatusID() == 1) {
                        Toast.makeText(context, "Thành Công", Toast.LENGTH_SHORT).show();


                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateStarReponse> call, Throwable t) {

            }
        });
    }

    public void UpdatePercent(UpdatePercentRequest updatePercentRequest) {
        Call<UpdatePercentRespone> updatePercentResponeCall = APIClient.UpdatePercent().updatepercent(Gobal.getGuiID(), updatePercentRequest);
        updatePercentResponeCall.enqueue(new Callback<UpdatePercentRespone>() {
            @Override
            public void onResponse(Call<UpdatePercentRespone> call, Response<UpdatePercentRespone> response) {

                if (response.isSuccessful()) {
                    UpdatePercentRespone updatePercentRespone = response.body();
                    if (updatePercentRespone.getStatusID() == 1) {
                        updateTask.update(1, taskslist);
                        Dialog dialogerro = new Dialog(context);
                        dialogerro.setContentView(R.layout.alert_dialog);
                        TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                        ImageView exit = dialogerro.findViewById(R.id.exit);
                        Button accept = dialogerro.findViewById(R.id.accept);
                        dialogerro.getWindow().setGravity(Gravity.CENTER);
                        dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                        dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialogerro.show();
                        ttvTitle.setText("Cập Nhật Thành Công");
                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerro.dismiss();

                            }
                        });
                        accept.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerro.dismiss();

                            }
                        });


                    } else {
                        Dialog dialogerro = new Dialog(context);
                        dialogerro.setContentView(R.layout.alert_dialog);
                        TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                        ImageView exit = dialogerro.findViewById(R.id.exit);
                        Button accept = dialogerro.findViewById(R.id.accept);
                        dialogerro.getWindow().setGravity(Gravity.CENTER);
                        dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                        dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialogerro.show();
                        ttvTitle.setText(updatePercentRespone.getErrorDescription());
                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerro.dismiss();
                            }
                        });
                        accept.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerro.dismiss();
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdatePercentRespone> call, Throwable t) {
                Dialog dialogerro = new Dialog(context);
                dialogerro.setContentView(R.layout.alert_dialog);
                TextView ttvTitle = dialogerro.findViewById(R.id.ttvTitle);
                ImageView exit = dialogerro.findViewById(R.id.exit);
                Button accept = dialogerro.findViewById(R.id.accept);
                dialogerro.getWindow().setGravity(Gravity.CENTER);
                dialogerro.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialogerro.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                dialogerro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogerro.show();
                ttvTitle.setText("Mất kết Nối");
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogerro.dismiss();
                    }
                });
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogerro.dismiss();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return taskslist == null ? 0 : taskslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTaksname, txtFromday, txtToday, txtPriority, txtTaskStatus, txtFinalPercent;
        public ImageView ImgStar, ImgMenu;
        GridLayout layout;
        CardView container;
        Button btnAccept, btnRefuse;
        ProgressBar process_star;

        public MyViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            ImgMenu = view.findViewById(R.id.dropdown_menu);
            txtTaksname = view.findViewById(R.id.taksname);
            txtFromday = view.findViewById(R.id.fromday);
            txtToday = view.findViewById(R.id.today);
            txtPriority = view.findViewById(R.id.Priority);
            txtTaskStatus = view.findViewById(R.id.TaskStatus);
            txtFinalPercent = view.findViewById(R.id.FinalPercent);
            ImgStar = view.findViewById(R.id.ImgStar);
            container = view.findViewById(R.id.container);
            process_star = view.findViewById(R.id.process_star);
//            btnAccept = view.findViewById(R.id.Accept);
//            btnRefuse = view.findViewById(R.id.Refuse);


//            itemView.setOnClickListener(this);
        }
    }


    public RecyclerEntity getEntity(int adapterPosition) {
        return tasks.get(adapterPosition);
    }

    public void undoDelete(RecyclerEntity entity, int position) {
        tasks.add(position, entity);
        notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        return taskslist.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    public void SetImgStar(Boolean isStar, ImageView imgStar) {
        if (isStar) {
            imgStar.setImageResource(star_check);
        } else {
            imgStar.setImageResource(star);
        }
    }

    //showloadding
    private class LoadingViewHolder extends TaskAdapter.MyViewHolder {
        ProgressBar progressBar;
        CardView container;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progessbar);
            container = itemView.findViewById(R.id.container);
        }
    }

    private void showLoadingView(LoadingViewHolder holder, int position) {
        //ProgressBar would be displayed
    }

}