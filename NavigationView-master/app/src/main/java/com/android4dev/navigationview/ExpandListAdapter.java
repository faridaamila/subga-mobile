package com.android4dev.navigationview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by S. Harsono on 2/3/2016.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter{
    private Context context;
    private ArrayList<Group> groups;

    public ExpandListAdapter(Context context, ArrayList<Group> groups) {
        this.context = context;
        this.groups = groups;
    }


    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();

        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Group group = (Group) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        tv.setText(group.getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Child child = (Child) getChild(groupPosition, childPosition);
        if (convertView ==  null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        //TextView tv = (TextView) convertView.findViewById(R.id.country_name);
        //tv.setText(child.getName().toString());


        TextView issue_date = (TextView)convertView.findViewById(R.id.issue_date);
        issue_date.setText(child.getIssue_date().toString());

        TextView from = (TextView)convertView.findViewById(R.id.from);
        from.setText(child.getFrom().toString());

        TextView to = (TextView)convertView.findViewById(R.id.to);
        to.setText(child.getTo().toString());

        EditText edit_from =(EditText)convertView.findViewById(R.id.editForm);
        edit_from.setText(child.getEditFrom().toString());

        EditText edit_to =(EditText) convertView.findViewById(R.id.editTo);
        edit_to.setText(child.getEditTo().toString());

        TextView keyword = (TextView)convertView.findViewById(R.id.keyword);
        keyword.setText(child.getKeyword().toString());

        TextView form_keyword = (TextView)convertView.findViewById(R.id.text_keyword);
        form_keyword.setText(child.getForm_keyword().toString());

        EditText editKeyword =(EditText) convertView.findViewById(R.id.editKeyword);
        editKeyword.setText(child.getEditKeyword().toString());


        // RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.layout);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
