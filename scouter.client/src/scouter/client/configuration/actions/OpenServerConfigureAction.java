/*
 *  Copyright 2015 the original author or authors. 
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 *
 */
package scouter.client.configuration.actions;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import scouter.client.configuration.views.ConfigureView;
import scouter.client.util.ImageUtil;

public class OpenServerConfigureAction extends Action {
	public final static String ID = OpenServerConfigureAction.class.getName();

	private final IWorkbenchWindow window;
	private int serverId;

	public OpenServerConfigureAction(IWorkbenchWindow window, String label, Image image, int serverId) {
		this.window = window;
		this.serverId = serverId;
		setText(label);
		setId(ID);
		setImageDescriptor(ImageUtil.getImageDescriptor(image));
	}

	public void run() {
		if (window != null) {
			try {
				ConfigureView v = (ConfigureView) window.getActivePage().showView(ConfigureView.ID, "" + serverId, IWorkbenchPage.VIEW_ACTIVATE);
				if(v!= null) {
					v.setInput(serverId);
				}
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
			}
		}
	}
}
