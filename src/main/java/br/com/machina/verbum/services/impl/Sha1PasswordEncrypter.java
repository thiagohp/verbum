// Copyright 2010 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package br.com.machina.verbum.services.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.machina.verbum.services.PasswordEncrypter;

/**
 * {@link PasswordEncrypter} implementation using SHA1.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class Sha1PasswordEncrypter implements PasswordEncrypter {
	
	final private static int ENCODED_LENGTH = 40;

	/**
	 * Encrypts the password if its length isn't 40 and returns the password unchanged otherwise.
	 */
	public String encrypt(String password) {
		
		if (password == null) {
			throw new IllegalArgumentException("Parameter password cannot be null.");
		}
		
		if (password.length() != ENCODED_LENGTH) {
			
			MessageDigest messageDigest = null;
			
			try {
				messageDigest = MessageDigest.getInstance("SHA1");
			}
			catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException(e.getMessage(), e);
			}
			
			messageDigest.reset();
			messageDigest.update(password.getBytes());

			final byte[] bytes = messageDigest.digest();
			String encrypted = new BigInteger(1, bytes).toString(16);
			
			if (encrypted.length() < 40) {
				
				final StringBuilder builder = new StringBuilder(encrypted);
				while (builder.length() < 40) {
					builder.insert(0, '0');
				}
				
				encrypted = builder.toString();
				
			}
			
		}
		
		return password;
		
	}

}
