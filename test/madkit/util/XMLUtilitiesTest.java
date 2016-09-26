/*
 * Copyright or © or Copr. Fabien Michel, Olivier Gutknecht, Jacques Ferber (1997)

fmichel@lirmm.fr
olg@no-distance.net
ferber@lirmm.fr

This software is a computer program whose purpose is to 
provide a lightweight Java library for designing and simulating Multi-Agent Systems (MAS).

This software is governed by the CeCILL-C license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL-C
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL-C license and that you accept its terms.
 */
package madkit.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import madkit.kernel.JunitMadkit;

import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * @author Fabien Michel
 * @since MadKit 5.0.2
 * @version 0.9
 * 
 */
public class XMLUtilitiesTest {

	@Test
	public void testGetFromCP() {
		try {
			assertNotNull(XMLUtilities.getDOM("madkit/xml/bench.xml"));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetFailFromIOException() {
			try {
				assertNull(XMLUtilities.getDOM("madkit/xml/notExist.xml"));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			JunitMadkit.noExceptionFailure();
	}

	@Test
	public void testGetFailFromWrongContent() {
			try {
				assertNull(XMLUtilities.getDOM("madkit/boot/process/test2.prop"));
			} catch (SAXException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			JunitMadkit.noExceptionFailure();
	}

	@Test
	public void testGetFromUserDir() {
		try {
			assertNotNull(XMLUtilities.getDOM("test/madkit/xml/bench.xml"));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetFromAbsolutePath() {
		File f = new File("test/madkit/xml/bench.xml");
		try {
			assertNotNull(XMLUtilities.getDOM(f.getAbsolutePath()));
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
