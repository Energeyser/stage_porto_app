package com.example.android.monitoringapp.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bitalino.comm.BITalinoDevice;
import com.bitalino.comm.BITalinoException;
import com.bitalino.comm.BITalinoFrame;

/**
 * 
 * @author bgamecho
 *
 */
public class BitalinoThread extends BTDeviceThread{

	public final static String TAG ="BITalinoThread";

	private int sample_rate;
	private int nFrames;
	private int packNum;
//	private int bitalinoMode; 
	private boolean downsample = false;
	
	private BITalinoDevice _bitalino_dev = null;
	
	/**
	 * BITalinoThreadConstructor
	 * @param myHandler
	 */
	public BitalinoThread(Handler myHandler, String remoteDevice) throws Exception {
		super(myHandler);

		this.setName("BITalinoThread");

		super.setupBT(remoteDevice);

		super.initComm();

		sendMessage("OK", "Connected to BITalino device at: "+_bluetoothDev.getAddress());

	}

	
	int[] channels; 
	public void setChannels(int[] channels){
		this.channels = channels;
	}
	
	@Override
	public void initialize() {
		super.initialize();
		 
		packNum = 0;
		_bitalino_dev = null;

		try {
			_bitalino_dev = new BITalinoDevice(sample_rate, channels);
			_bitalino_dev.open(_inStream, _outStream);
			_bitalino_dev.start();
			
			
		} catch (BITalinoException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void loop() {

		try {
			BITalinoFrame[] frames = new BITalinoFrame[nFrames];
			
			frames = _bitalino_dev.read(nFrames);
			
			if(downsample){
				BITalinoFrame[] halfFrames = new BITalinoFrame[nFrames/2];
				for(int i = 0; i<halfFrames.length; i++){
					halfFrames[i]= frames[i*2]; 			
				}
				frames = halfFrames;
				//Log.v(TAG, "Downsample: enable");
			}

			
			//Send the data
			Message msg = new Message();
		
			Bundle myDataBundle = new Bundle();
			myDataBundle.putSerializable("bitalino_data", frames);
			msg.setData(myDataBundle);
			myHandler.sendMessage(msg);       		

		} catch (BITalinoException e1) {
			Log.e(TAG, "Error with Bitalino");
			e1.printStackTrace();
		}

	}

	@Override
	public void close() {
		
		if(_bitalino_dev!=null){
			// Stop the data acquisition
			try {
				_bitalino_dev.stop();
			} catch (BITalinoException e) {
				Log.e(TAG, "Problems closing the BITalino device");
				e.printStackTrace();
			}
		}
		super.close();
	}

	public int getSampleRate() {
		return sample_rate;
	}

	public void setSampleRate(int sample_rate) {
		this.sample_rate = sample_rate;
	}

	public int getNumFrames() {
		return nFrames;
	}

	public void setNumFrames(int nFrames) {
		this.nFrames = nFrames;
	}
	
	public void setDownsamplingOn(boolean downsample){
		this.downsample = downsample;
		
	}
}