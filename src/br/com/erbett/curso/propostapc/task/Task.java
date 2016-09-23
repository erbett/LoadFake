/* 
 * Copyright 2016 Erbett H. R. Oliveira, Inc. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 		
 * 		1. Redistributions of source code must retain the above copyright notice, this list of
 * 			conditions and the following disclaimer.
 * 
 * 		2. Redistributions in binary form must reproduce the above copyright notice, this list
 * 		   of conditions and the following disclaimer in the documentation and/or other materials
 *         provided with the distribution.
 *         
 * THIS SOFTWARE IS PROVIDED BY ERBETT HINTON RIBEIRO OLIVEIRA, INC. ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, AND LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ERBETT HINTON RIBEIRO OLIVEIRA, INC. OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package br.com.erbett.curso.propostapc.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.erbett.curso.propostapc.MainActivity;
import br.com.erbett.curso.propostapc.MainActivityListener;

public class Task extends AsyncTask<Void, Void, String> {

	private ProgressDialog progress;
	private Context context;
	private MainActivityListener listener;
	
	public Task(Context context) {
		this.context = context;
		
		if (context instanceof MainActivity){
			listener = (MainActivityListener) context;
		}
	}
	
	@Override
	protected void onPreExecute() {
		
		progress = new ProgressDialog(context);
		progress.setIndeterminate(true);
		progress.setCancelable(true);
		progress.setCanceledOnTouchOutside(false);
		progress.setMessage(TaskStrings.MENG_DIALOG);
		progress.show();
	}
	
	
	@Override
	protected String doInBackground(Void... params) {
		
		StringBuffer result = new StringBuffer();
		
		result.append(TaskStrings.MENG_SO+listener.getSistemaOperacional());
		result.append(TaskStrings.MENG_SFTW+listener.getSoftwares());
		
		try {
			Thread.sleep(6000);//espera 6 segundos
		} catch (InterruptedException e) {
			Log.e(TaskStrings.TAG, e.getMessage());
		}
		
		return result.toString();
	}
	
	@Override
	protected void onPostExecute(String result) {

		if (progress != null){
			progress.dismiss();
		}
		
		listener.resultadoTask(result);
	}

}
