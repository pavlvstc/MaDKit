/*
 * Copyright 1997-2012 Fabien Michel, Olivier Gutknecht, Jacques Ferber
 * 
 * This file is part of MadKit.
 * 
 * MadKit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * MadKit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with MadKit. If not, see <http://www.gnu.org/licenses/>.
 */
package madkit.simulation.overlooker;

import static org.junit.Assert.assertEquals;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Activator;
import madkit.kernel.JunitMadKit;
import madkit.kernel.Madkit.LevelOption;
import madkit.kernel.Scheduler;
import madkit.testing.util.agent.SimulatedAgent;

import org.junit.Test;

/**
 * @author Fabien Michel
 * @since MadKit 5.0.0.15
 * @version 0.9
 * 
 */
public class BuggyOverlooker extends JunitMadKit {

	@SuppressWarnings("serial")
	@Test
	public void buggyActivator() {
		String[] myArgs = { LevelOption.kernelLogLevel.toString(), "ALL" };
		addMadkitArgs(myArgs);
		launchTest(new Scheduler() {

			@Override
			protected void activate() {
				addActivator(new Activator<AbstractAgent>(COMMUNITY, GROUP, ROLE) {

					@SuppressWarnings("null")
					@Override
					protected void adding(AbstractAgent theAgent) {
						Object o = null;
						o.toString();
					}
				});
				assertEquals(ReturnCode.AGENT_CRASH, launchAgent(new SimulatedAgent()));
			}
		});
	}
}
