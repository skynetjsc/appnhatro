package com.skynet.thuenha.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skynet.thuenha.R;
import com.skynet.thuenha.interfaces.ICallback;
import com.skynet.thuenha.models.Banner;
import com.skynet.thuenha.ui.views.SlideLoopAdapter;
import com.skynet.thuenha.ui.views.SlideView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlidePhotoHomeAdapter extends SlideLoopAdapter {
    List<Banner> urlPhotos;
    ICallback iCallback;

    public SlidePhotoHomeAdapter(SlideView viewPager, List<Banner> urlPhotos, ICallback iCallback) {
        super(viewPager);
        this.urlPhotos = urlPhotos;
        this.iCallback = iCallback;

    }

    public void setUrlPhotos(List<String> urlPhotos) {
//        if (this.urlPhotos == null) this.urlPhotos = new ArrayList<>();
//        this.urlPhotos.clear();
//        for (int i = 0; i < urlPhotos.size(); i++) {
//            if (urlPhotos.get(i) != null && !urlPhotos.get(i).isEmpty()) {
//                this.urlPhotos.add(urlPhotos.get(i));
//            }
//        }

    }

    @Override
    public View getView(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.home_item_photo_slide, container, false);
        ImageView imageView = view.findViewById(R.id.img);
        if (urlPhotos.get(position) == null || urlPhotos.get(position).getImg().isEmpty())
            return view;
        Picasso.with(container.getContext()).load(urlPhotos.get(position).getImg()).fit().centerCrop().into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
            }
        });
        return view;
    }

    @Override
    protected int getRealCount() {
        return urlPhotos.size();
    }
}
