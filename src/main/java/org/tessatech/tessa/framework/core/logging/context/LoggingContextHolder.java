/*
 * Copyright (c) 2015-2018 TessaTech LLC.
 *
 * Licensed under the MIT License (the "License"); you may not use this file
 *  except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 *
 */

package org.tessatech.tessa.framework.core.logging.context;

import org.tessatech.tessa.framework.core.exception.system.InternalException;

import java.util.Optional;

public class LoggingContextHolder
{

	private static final ThreadLocal<LoggingContext> loggingContextLocal = new ThreadLocal<>();

	public static void setContext(LoggingContext context)
	{
		loggingContextLocal.set(context);
	}

	public static void clearContext()
	{
		loggingContextLocal.set(null);
		loggingContextLocal.remove();
	}

	public static boolean isPresent()
	{
		return (loggingContextLocal.get() != null);
	}

	public static LoggingContext getContext()
	{
		LoggingContext context = loggingContextLocal.get();

		if (context == null)
		{
			throw new InternalException("LoggingContext was requested but is Null");
		}

		return context;
	}

	public static Optional<LoggingContext> getContextOptional()
	{
		return Optional.ofNullable(loggingContextLocal.get());
	}

}
