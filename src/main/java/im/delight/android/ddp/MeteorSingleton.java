package im.delight.android.ddp;

/**
 * Copyright 2014 www.delight.im <info@delight.im>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/** Provides a single access point to the `Meteor` class that can be used across `Activity` instances */
public class MeteorSingleton extends Meteor {

	private static MeteorSingleton mInstance;

	public synchronized static MeteorSingleton createInstance(final String serverUri) {
		return createInstance(serverUri, null);
	}

	public synchronized static MeteorSingleton createInstance(final String serverUri, final String protocolVersion) {
		if (mInstance != null) {
			throw new RuntimeException("An instance has already been created");
		}

		if (protocolVersion == null) {
			mInstance = new MeteorSingleton(serverUri);
		}
		else {
			mInstance = new MeteorSingleton(serverUri, protocolVersion);
		}

		return mInstance;
	}

	public synchronized static MeteorSingleton getInstance() {
		if (mInstance == null) {
			throw new RuntimeException("Please call `createInstance(...)` first");
		}

		return mInstance;
	}

	public synchronized static boolean hasInstance() {
		return mInstance != null;
	}

	private MeteorSingleton(final String serverUri) {
		super(serverUri);
	}

	private MeteorSingleton(final String serverUri, final String protocolVersion) {
		super(serverUri, protocolVersion);
	}

}
