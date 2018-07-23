package com.danielsaraiva1.sendemail;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Mail mail;
    public static Context mContext;
    MainActivity mActivity;
    String mensagem = "";

    public SendEmailAsyncTask(Context context, MainActivity activity) {
        mContext = context;
        mActivity = activity;

    }

    public SendEmailAsyncTask() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if (mail.send()) {
                mensagem = mContext.getString(R.string.emailEnviado);
                //activity.displayMessage("Email failed to send.");
            }
            else {
                mensagem = mContext.getString(R.string.falhaEnvioEmail);
            }
             mActivity.mensagemRetorno(mensagem);
            return true;
        } catch (AuthenticationFailedException e) {
            Log.e(SendEmailAsyncTask.class.getName(), mContext.getString(R.string.problemaInfoconta));
            e.printStackTrace();
            mActivity.mensagemRetorno(mContext.getString(R.string.falhaAutenticacao));
            return false;
        } catch (MessagingException e) {
            Log.e(SendEmailAsyncTask.class.getName(),mContext.getString(R.string.emailFailed));
            e.printStackTrace();
            mActivity.mensagemRetorno(mContext.getString(R.string.emailFailedSend));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            mActivity.mensagemRetorno(mContext.getString(R.string.erroNaoTratado));
            return false;
        }
    }
}