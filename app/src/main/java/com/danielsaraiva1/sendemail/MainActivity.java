package com.danielsaraiva1.sendemail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btEnviar)
    public void enviar(Button button){
        enviarMensagem();
    }


    private void enviarMensagem() {
        progressBar.setVisibility(View.VISIBLE);
        String[] recipients = {getString(R.string.destinatario)};
        SendEmailAsyncTask email = new SendEmailAsyncTask(this, MainActivity.this);
        email.mail = new Mail(getString(R.string.usuario), getString(R.string.senha));
        email.mail.set_from(getString(R.string.usuario));
        email.mail.setBody(getString(R.string.corpo_email));
        email.mail.set_to(recipients);
        email.mail.set_subject(getString(R.string.assunto));
        email.execute();
    }



    public void mensagemRetorno(final String message) {
        new Thread()
        {
            public void run()
            {
                MainActivity.this.runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        //Do your UI operations like dialog opening or Toast here
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }
}
