/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/**
 * Start time:13:17:28 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
package org.mobicents.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:17:28 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface InformationRequestIndicators extends ISUPParameter {
	public static final int _PARAMETER_CODE = 0x0E;

	/**
	 * Flag that indicates that information is requested
	 */
	public static final boolean _INDICATOR_REQUESTED = true;

	/**
	 * Flag that indicates that information is not requested
	 */
	public static final boolean _INDICATOR_NOT_REQUESTED = false;
	
	public boolean isCallingPartAddressRequestIndicator();

	public void setCallingPartAddressRequestIndicator(boolean callingPartAddressRequestIndicator);

	public boolean isHoldingIndicator();

	public void setHoldingIndicator(boolean holdingIndicator);

	public boolean isCallingpartysCategoryRequestIndicator();

	public void setCallingpartysCategoryRequestIndicator(boolean callingpartysCategoryRequestIndicator);

	public boolean isChargeInformationRequestIndicator();

	public void setChargeInformationRequestIndicator(boolean chargeInformationRequestIndicator);

	public boolean isMaliciousCallIdentificationRequestIndicator();

	public void setMaliciousCallIdentificationRequestIndicator(boolean maliciousCallIdentificationRequestIndicator);

	public int getReserved();

	public void setReserved(int reserved);

}
