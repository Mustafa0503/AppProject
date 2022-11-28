//package com.example.degreeplanner;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.ContentInfo;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
//
//    View view;
//    Context context;
//    ArrayList<String > arrayList;
//    CourseListener courseListener;
//    ArrayList<String > arrayList_0 = new ArrayList<>();
//    public CourseAdapter(Context context, ArrayList<String> arrayList, CourseListener courseListener) {
//        this.context = context;
//
//        this.arrayList = arrayList;
//        this.courseListener = courseListener;
//    }
//
//    public View getView() {
//        return view;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        view = LayoutInflater.from(context).inflate(R.layout.rv_layout, parent, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        if(arrayList!=null&&arrayList.size()>0){
//            holder.check_box.setTag(arrayList.get(position));
//            holder.check_box.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(holder.check_box.isChecked()){
//                        arrayList_0.add(arrayList.get(position));
//
//                    }else{
//                        arrayList_0.remove(arrayList.get(position));
//                    }
//                    courseListener.onCourseChange(arrayList_0);
//                }
//            });
//
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        CheckBox check_box;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            check_box = itemView.findViewById(R.id.check_box);
//        }
//    }
//}
