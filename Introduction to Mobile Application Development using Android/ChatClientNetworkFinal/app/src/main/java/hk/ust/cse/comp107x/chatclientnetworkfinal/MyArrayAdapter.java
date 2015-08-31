package hk.ust.cse.comp107x.chatclientnetworkfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by muppala on 4/5/15.
 */
public class MyArrayAdapter extends ArrayAdapter<Contacts.FriendInfo> {
    private final Context context;
    private final List<Contacts.FriendInfo> friendInfoList;

    public MyArrayAdapter(Context context, List<Contacts.FriendInfo> friendInfoList) {
        super(context, R.layout.friend_item, friendInfoList);
        this.context = context;
        this.friendInfoList = friendInfoList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View friendInfoView;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Change the layout based on who the message is from
        friendInfoView = inflater.inflate(R.layout.friend_item, parent, false);
        TextView friendName = (TextView) friendInfoView.findViewById(R.id.friendName);
        friendName.setText(friendInfoList.get(position).name);
        TextView statusMsg = (TextView) friendInfoView.findViewById(R.id.statusMsg);
        statusMsg.setText(friendInfoList.get(position).statusMsg);

        ImageView imageView = (ImageView) friendInfoView.findViewById(R.id.avatar);

        // TODO Asynchronously load the images from the server using Picasso.
        try{
            Picasso.with(context)
                    .load(Constants.SERVER+friendInfoList.get(position).imageURL)
                    .resize(50, 50)
                    .centerCrop()
                    .into(imageView);
        }catch(Exception e){
            Picasso.with(context).load("file:///ic_launcher/ic_launcher.png")
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }

        return friendInfoView;
    }
}
