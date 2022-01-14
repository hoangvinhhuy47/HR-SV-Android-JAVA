package com.example.myapplication.API;

import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Service.AcceptEquipmentRecoverySerVice;
import com.example.myapplication.Service.AcceptEquitmentAssimentService;
import com.example.myapplication.Service.AddLeaveApplicationSevice;
import com.example.myapplication.Service.ApprovalService;
import com.example.myapplication.Service.ChangePasswordSevice;
import com.example.myapplication.Service.CheckInService;
import com.example.myapplication.Service.DataMangerSevice;
import com.example.myapplication.Service.DeleteLeaveApplicationService;
import com.example.myapplication.Service.DeleteRegisterWorikingService;
import com.example.myapplication.Service.GetAllEquipmentAssignmentInfoService;
import com.example.myapplication.Service.GetAllEquipmentReCoveryInfoService;
import com.example.myapplication.Service.GetDataEquipmentAssigmentService;
import com.example.myapplication.Service.GetDataEquipmentRecoveryService;
import com.example.myapplication.Service.GetDataSymbolSerVice;
import com.example.myapplication.Service.GetProfileService;
import com.example.myapplication.Service.LeaveApplicationSerVice;
import com.example.myapplication.Service.AddRegisterWorkingService;
import com.example.myapplication.Service.RegisterWorkingSevice;
import com.example.myapplication.Service.UpdateLeaveApplicationService;
import com.example.myapplication.Service.UpdateProfileService;
import com.example.myapplication.Service.UpdateRegisterApplicationService;
import com.example.myapplication.Service.WorkingAttendaceSerVice;
import com.example.myapplication.Service.DenyService;
import com.example.myapplication.Service.LoadBeginCheckInSerVice;
import com.example.myapplication.Service.UpdatePercentService;
import com.example.myapplication.Service.UpdateStarService;
import com.example.myapplication.Service.UserService;
import com.example.myapplication.Service.SettingConfigSerVice;
import com.example.myapplication.Service.TaskService;
import com.example.myapplication.Service.WorkingEmployeeService;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit getRetrofit() {
//     ABC abc = new ABC();
//        abc.getWeb();
//        String url = getAbc().getWeb();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Gobal.getWebAPI())
                .build();
        return retrofit;
    }
    //https://hr.softwareviet.com/api/hr/
    public static SettingConfigSerVice getSettingConfigSerVice() {
        SettingConfigSerVice settingConfigSerVice = getRetrofit().create(SettingConfigSerVice.class);
        return settingConfigSerVice;
    }
    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }
    public static CheckInService CheckInService(){
        CheckInService checkInService = getRetrofit().create(CheckInService.class);
        return checkInService;
    }
    public static TaskService getCongViecSerVice(){
        TaskService taskService = getRetrofit().create(TaskService.class);
        return taskService;
    }
    public static UpdateStarService updateStarService(){
        UpdateStarService updateStarService = getRetrofit().create(UpdateStarService.class);
        return updateStarService;
    }
    public static ApprovalService getApprovalService(){
        ApprovalService approvalService = getRetrofit().create(ApprovalService.class);
        return approvalService;
    }
    public static DenyService DenyService(){
        DenyService denyService = getRetrofit().create(DenyService.class);
        return denyService;
    }
    public static UpdatePercentService UpdatePercent(){
        UpdatePercentService updatePercentService = getRetrofit().create(UpdatePercentService.class);
        return updatePercentService;
    }
    public static WorkingAttendaceSerVice DataWorkingAttendance(){
        WorkingAttendaceSerVice workingAttendaceSerVice = getRetrofit().create(WorkingAttendaceSerVice.class);
        return workingAttendaceSerVice;
    }
  public static LoadBeginCheckInSerVice LoadBegin(){
        LoadBeginCheckInSerVice loadBeginCheckInSerVice = getRetrofit().create(LoadBeginCheckInSerVice.class);
        return loadBeginCheckInSerVice;
  }
  public static WorkingEmployeeService DataWorkingEmployee(){
        WorkingEmployeeService workingEmployeeService = getRetrofit().create(WorkingEmployeeService.class);
        return workingEmployeeService;
  }
      public static LeaveApplicationSerVice DataLeaveAplication(){
        LeaveApplicationSerVice leaveApplicationSerVice = getRetrofit().create(LeaveApplicationSerVice.class);
        return leaveApplicationSerVice;
  }
    public static AddLeaveApplicationSevice DataAddLeaveApplication(){
      AddLeaveApplicationSevice addLeaveApplicationSevice = getRetrofit().create(AddLeaveApplicationSevice.class);
      return addLeaveApplicationSevice;
    }
    public static UpdateLeaveApplicationService DataUpdateleave(){
        UpdateLeaveApplicationService updateLeaveApplicationService = getRetrofit().create(UpdateLeaveApplicationService.class);
        return updateLeaveApplicationService;
    }
    public static AddRegisterWorkingService DataRegisterWorking(){
        AddRegisterWorkingService addRegisterWorkingService = getRetrofit().create(AddRegisterWorkingService.class);
        return addRegisterWorkingService;
    }
    public static RegisterWorkingSevice DataRigister(){
        RegisterWorkingSevice    registerWorkingSevice = getRetrofit().create(RegisterWorkingSevice.class);
        return registerWorkingSevice;
    }
    public static UpdateRegisterApplicationService InfomationRegister(){
        UpdateRegisterApplicationService updateRegisterApplicationService = getRetrofit().create(UpdateRegisterApplicationService.class);
        return updateRegisterApplicationService;
    }
    public static DeleteLeaveApplicationService DeleLeaveApplication(){
        DeleteLeaveApplicationService deleteLeaveApplicationService = getRetrofit().create(DeleteLeaveApplicationService.class);
        return deleteLeaveApplicationService;
    }
    public static DeleteRegisterWorikingService DeleRegister(){
        DeleteRegisterWorikingService deleteRegisterWorikingService = getRetrofit().create(DeleteRegisterWorikingService.class);
        return deleteRegisterWorikingService;
    }
    public static DataMangerSevice dataMangerSevice(){
        DataMangerSevice dataMangerSevice = getRetrofit().create(DataMangerSevice.class);
        return  dataMangerSevice;
    }
    public static GetDataSymbolSerVice getDataSymbolSerVice(){
        GetDataSymbolSerVice getDataSymbolSerVice =getRetrofit().create(GetDataSymbolSerVice.class);
        return  getDataSymbolSerVice;
    }
    public static GetDataEquipmentAssigmentService getDataEquipmentEmployeeService(){
        GetDataEquipmentAssigmentService getDataEquipmentAssigmentService =getRetrofit().create(GetDataEquipmentAssigmentService.class);
        return getDataEquipmentAssigmentService;
    }
    public static GetAllEquipmentAssignmentInfoService getAllEquipmentAssignmentInfoService(){
        GetAllEquipmentAssignmentInfoService getAllEquipmentAssignmentInfoService =getRetrofit().create(GetAllEquipmentAssignmentInfoService.class);
        return  getAllEquipmentAssignmentInfoService;
    }
    public static GetDataEquipmentRecoveryService getDataEquipmentRecoveryService (){
        GetDataEquipmentRecoveryService getDataEquipmentRecoveryService = getRetrofit().create(GetDataEquipmentRecoveryService.class);
        return getDataEquipmentRecoveryService;
    }
    public static GetAllEquipmentReCoveryInfoService getAllEquipmentReCoveryInfoService(){
        GetAllEquipmentReCoveryInfoService getAllEquipmentReCoveryInfoService =getRetrofit().create(GetAllEquipmentReCoveryInfoService.class);
        return  getAllEquipmentReCoveryInfoService;
    }
    public static AcceptEquipmentRecoverySerVice acceptEquipmentRecoverySerVice(){
        AcceptEquipmentRecoverySerVice acceptEquipmentRecoverySerVice =getRetrofit().create(AcceptEquipmentRecoverySerVice.class);
        return  acceptEquipmentRecoverySerVice;
    }
    public static AcceptEquitmentAssimentService acceptEquitmentAssimentService(){
        AcceptEquitmentAssimentService acceptEquitmentAssimentService = getRetrofit().create(AcceptEquitmentAssimentService.class);
        return  acceptEquitmentAssimentService;
    }
    public static GetProfileService getProfileService(){
        GetProfileService getProfileService =getRetrofit().create(GetProfileService.class);
        return  getProfileService;
    }
    public static UpdateProfileService updateProfileService(){
        UpdateProfileService updateProfileService =getRetrofit().create(UpdateProfileService.class);
        return updateProfileService;
    }
    public static ChangePasswordSevice changePasswordSevice(){
        ChangePasswordSevice changePasswordSevice =getRetrofit().create(ChangePasswordSevice.class);
        return  changePasswordSevice;
    }

}
