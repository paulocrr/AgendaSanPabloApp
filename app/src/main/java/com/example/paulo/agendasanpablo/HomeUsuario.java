package com.example.paulo.agendasanpablo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.clans.fab.FloatingActionButton;

import java.util.HashMap;

public class HomeUsuario extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    private SliderLayout mDemoSlider;
    TextView mensaje;
    FloatingActionButton logout;
    FloatingActionButton calculadoraNota;
    FloatingActionButton listarProfesores;
    FloatingActionButton verHorarios;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
        mensaje = (TextView) findViewById(R.id.mensajeBienvenida);
        calculadoraNota = (FloatingActionButton) findViewById(R.id.fabNotas);
        logout =(FloatingActionButton) findViewById(R.id.fabLogout);
        listarProfesores = (FloatingActionButton) findViewById(R.id.fabCalificar);
        verHorarios = (FloatingActionButton) findViewById(R.id.fabHorario);
        prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "http://liderazgo.ucsp.edu.pe/wp-content/uploads/2017/02/Banner-web.jpg");
        url_maps.put("2", "http://ucsp.edu.pe/iet/extension/wp-content/uploads/2016/03/Banner-horizontal-18-1024x267.jpg");
        url_maps.put("3", "http://ucsp.edu.pe/wp-content/uploads/2017/06/Banner-horizontal-10.jpg");
        url_maps.put("4", "http://ucsp.edu.pe/wp-content/uploads/2017/06/Banner-horizontal-8.jpg");
        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        String nom = prefs.getString("nombre","");
        mensaje.setText("Bienvenida/o: "+ nom);
        calculadoraNota.setOnClickListener(clickListener);
        logout.setOnClickListener(clickListener);
        listarProfesores.setOnClickListener(clickListener);
        verHorarios.setOnClickListener(clickListener);


    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fabNotas:
                    Intent i = new Intent(HomeUsuario.this,CalculadoraNotas.class);
                    startActivity(i);
                    break;
                case R.id.fabLogout:
                    SharedPreferences prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.clear();
                    editor.commit();
                    Intent a = new Intent(HomeUsuario.this,MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.fabCalificar:
                    Intent b = new Intent(HomeUsuario.this,ListadoProfesores.class);
                    startActivity(b);
                    break;
                case R.id.fabHorario:
                    Intent c = new Intent(HomeUsuario.this,VerHorarios.class);
                    startActivity(c);
                    break;

            }
        }
    };
    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
