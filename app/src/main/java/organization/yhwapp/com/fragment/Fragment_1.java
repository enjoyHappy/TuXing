package organization.yhwapp.com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;

import organization.yhwapp.com.R;
import organization.yhwapp.com.activity.MessageActivity;
import organization.yhwapp.com.autoscrollupview.AutoScrollUpView;
import organization.yhwapp.com.bean.ItemBanner;
import organization.yhwapp.com.utils.IntentUtil;
import organization.yhwapp.com.utils.ToastUtil;
import organization.yhwapp.com.view.HYViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_1 extends Fragment {

    @ViewInject(R.id.vp_banner)
    HYViewPager viewPager;
    @ViewInject(R.id.AutoScrollUpView)
    AutoScrollUpView upView;

    String[] imgUrl = new String[]{
            "http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageMogr2/thumbnail/!50p",
            "http://img3.imgtn.bdimg.com/it/u=3552509992,144420107&fm=21&gp=0.jpg",
            "http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageMogr2/thumbnail/!x75p",
            "http://img5.pcpop.com/ArticleImages/picshow/0x0/20130204/2013020418031962500.jpg",
            "http://fengniao.fn.img-space.com/g1/M00/04/78/Cg-4q1YI2RGINUtSAASwuwDOF9YAAMcTgFlcPcABLDT359.jpg"
    };

    @OnClick(R.id.message)
    public void msg(View v){
        IntentUtil.openActivity(getActivity(), MessageActivity.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_1, container, false);
        ViewUtils.inject(this, v);

        initBanner();
        initUpView();

        initListener();
        return v;
    }

    private void initUpView() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("习近平治党思路：把制度的笼子扎得更紧");
        list.add("李克强：给企业减税降费是积极的财政政策");
        list.add("洪秀柱徒步拜谒中山陵 周围群众欢呼声不断");
        list.add("深圳水贝村村民谈“拆迁补2亿”：一两千万是有的");
        upView.setData(list);
        upView.setTimer(6000);
        upView.start();
    }

    private void initBanner() {
        ArrayList<ItemBanner> itemBanners = new ArrayList<ItemBanner>();

        ArrayList<ImageView> list = new ArrayList<>();
        for (int i = 0; i < imgUrl.length; i++) {
            ItemBanner banner = new ItemBanner();
            banner.imgUrl = imgUrl[i];
            itemBanners.add(banner);

            ImageView img = new ImageView(getActivity());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(banner.imgUrl).into(img);
            list.add(img);
        }

        viewPager.setViews(list, true);
        viewPager.setAUTOSpace(3000);
    }

    private void initListener() {
        viewPager.setOnBannerClick(new HYViewPager.OnBannerClick() {
            @Override
            public void bannerClick(int realPosition) {
                ToastUtil.showToast(getActivity(),realPosition+"");
            }
        });
    }

}
