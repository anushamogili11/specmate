package com.specmate.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import com.mifmif.common.regex.Generex;
import com.specmate.common.SpecmateException;
import com.specmate.model.base.BaseFactory;
import com.specmate.model.base.Folder;
import com.specmate.persistency.IChange;
import com.specmate.persistency.IChangeListener;
import com.specmate.persistency.ITransaction;
import com.specmate.persistency.IValidator;

public class CDOPersistencyValidationTest extends IntegrationTestBase {

	public CDOPersistencyValidationTest() throws Exception {
		super();
	}

	@Test
	public void testIDValidCharacters() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		try {
			ITransaction t = persistency.openTransaction(validators);
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					folder.setId("tEst_1-2");
					t.getResource().getContents().add(folder);
					return null;
				}
			});
			t.close();
		} catch (SpecmateException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testIDInvalidCharacters() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		Generex generex = new Generex("test-[^a-zA-Z_0-9\\-]_case");
		generex.setSeed(System.currentTimeMillis());
		ITransaction t = null;

		for (int i = 0; i < 10; i++) {
			try {
				t = persistency.openTransaction(validators);
				Resource r = t.getResource();
				t.doAndCommit(new IChange<Object>() {
					@Override
					public Object doChange() throws SpecmateException {
						Folder folder = BaseFactory.eINSTANCE.createFolder();
						String id = generex.random(1, 1);
						folder.setId(id);
						r.getContents().add(folder);
						return null;
					}
				});
				fail("Invalid id not detected");
			} catch (SpecmateException e) {
				// All OK
			} finally {
				if (t != null) {
					t.close();
				}
			}
		}
	}

	@Test
	public void testIDEmptyNull() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					r.getContents().add(folder);
					return null;
				}
			});
			fail("Null id not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testIDEmptyString() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					folder.setId("");
					r.getContents().add(folder);
					return null;
				}
			});
			fail("Empty id not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			t.close();
		}
	}

	@Test
	public void testIDEmptySpace() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					folder.setId(" ");
					r.getContents().add(folder);
					return null;
				}
			});
			fail("Space id not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			t.close();
		}
	}

	@Test
	public void testNameNull() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.NAME));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					r.getContents().add(folder);
					return null;
				}
			});
			fail("Null folder name not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testNameEmptyString() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.NAME));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					folder.setName("");
					r.getContents().add(folder);
					return null;
				}
			});
			fail("Empty folder name not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testNameSpace() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.NAME));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					folder.setName(" ");
					r.getContents().add(folder);
					return null;
				}
			});
			fail("Space folder name not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testNameInvalidChars() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.NAME));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder folder = BaseFactory.eINSTANCE.createFolder();
					folder.setName("This;");
					r.getContents().add(folder);
					return null;
				}
			});
			fail("Invalid name not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testUniqueID() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder parent = BaseFactory.eINSTANCE.createFolder();
					parent.setId("parent");
					Folder child1 = BaseFactory.eINSTANCE.createFolder();
					child1.setId("child1");
					Folder child2 = BaseFactory.eINSTANCE.createFolder();
					child2.setId("child2");

					parent.getContents().add(child1);
					parent.getContents().add(child2);
					r.getContents().add(parent);
					return null;
				}
			});
		} catch (SpecmateException e) {
			fail(e.getCause().getMessage());
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testUniqueIDUnderSameParent() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder parent = BaseFactory.eINSTANCE.createFolder();
					parent.setId("parent");
					Folder child1 = BaseFactory.eINSTANCE.createFolder();
					child1.setId("child1");
					Folder child2 = BaseFactory.eINSTANCE.createFolder();
					child2.setId("child2");

					Folder child_clone = BaseFactory.eINSTANCE.createFolder();
					child_clone.setId(child1.getId());

					parent.getContents().add(child1);
					parent.getContents().add(child2);
					parent.getContents().add(child_clone);
					r.getContents().add(parent);
					return null;
				}
			});
			fail("Add the same node twice in tree");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testSameIDinDifferentBranch() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder parent = BaseFactory.eINSTANCE.createFolder();
					parent.setId("parent");
					Folder child1 = BaseFactory.eINSTANCE.createFolder();
					child1.setId("child1");
					Folder child2 = BaseFactory.eINSTANCE.createFolder();
					child2.setId("child2");

					Folder grandchild = BaseFactory.eINSTANCE.createFolder();
					grandchild.setId("grandchild");

					parent.getContents().add(child1);
					parent.getContents().add(child2);
					child1.getContents().add(grandchild);
					child2.getContents().add(grandchild);
					r.getContents().add(parent);
					return null;
				}
			});
		} catch (SpecmateException e) {
			fail("Siblings can have children with the same id");
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testAddIdenticalObject() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder parent = BaseFactory.eINSTANCE.createFolder();
					parent.setId("parent");
					Folder child1 = BaseFactory.eINSTANCE.createFolder();
					child1.setId("child1");
					Folder child2 = BaseFactory.eINSTANCE.createFolder();
					child2.setId("child2");

					Folder grandchild = BaseFactory.eINSTANCE.createFolder();
					grandchild.setId("grandchild");

					parent.getContents().add(child1);
					parent.getContents().add(child2);
					child1.getContents().add(grandchild);
					child1.getContents().add(grandchild); // Adding the same object to the list has no effect, i.e. the
															// list is rather a set
					assertEquals(1, child1.getContents().size());
					r.getContents().add(parent);
					return null;
				}
			});
		} catch (SpecmateException e) {
			fail(e.getMessage());
		} finally {
			if (t != null) {
				t.close();
			}
		}

	}

	@Test
	public void testUniqueIDinSameBranch() {
		List<IChangeListener> validators = new ArrayList<>();
		validators.add(persistency.getValidator(IValidator.Type.ID));

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder parent = BaseFactory.eINSTANCE.createFolder();
					parent.setId("parent");
					Folder child1 = BaseFactory.eINSTANCE.createFolder();
					child1.setId("child1");
					Folder child2 = BaseFactory.eINSTANCE.createFolder();
					child2.setId("child2");

					Folder grandchild = BaseFactory.eINSTANCE.createFolder();
					grandchild.setId("grandchild");
					Folder grandchild_clone = BaseFactory.eINSTANCE.createFolder();
					grandchild_clone.setId(grandchild.getId());

					parent.getContents().add(child1);
					parent.getContents().add(child2);
					child1.getContents().add(grandchild);
					child1.getContents().add(grandchild_clone);
					assertEquals(2, child1.getContents().size());
					r.getContents().add(parent);
					return null;
				}
			});
			fail("Could add the same node twice in tree");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}

	@Test
	public void testTopLevelFolder() {
		List<IChangeListener> validators = new ArrayList<>();

		ITransaction t = null;

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder parent = BaseFactory.eINSTANCE.createFolder();
					parent.setId("parent");
					Folder child1 = BaseFactory.eINSTANCE.createFolder();
					child1.setId("child1");
					Folder child2 = BaseFactory.eINSTANCE.createFolder();
					child2.setId("child2");

					parent.getContents().add(child1);
					parent.getContents().add(child2);
					r.getContents().add(parent);
					return null;

				}
			});
		} catch (SpecmateException e) {
			fail(e.getMessage());
		} finally {
			if (t != null) {
				t.close();
			}
		}

		validators.add(persistency.getValidator(IValidator.Type.TOPLEVELFOLDER));

		try {
			t = persistency.openTransaction(validators);
			Resource r = t.getResource();
			t.doAndCommit(new IChange<Object>() {
				@Override
				public Object doChange() throws SpecmateException {
					Folder child3 = BaseFactory.eINSTANCE.createFolder();
					child3.setId("child3");

					Folder project = (Folder) r.getContents().get(0);
					Folder topLevelFolder = (Folder) project.getContents().get(0);
					assertTrue(topLevelFolder.getContents().add(child3)); // This is allowed
					assertTrue(project.getContents().add(child3)); // This not
					return null;
				}
			});
			fail("Top level folder violation not detected");
		} catch (SpecmateException e) {
			// All OK
		} finally {
			if (t != null) {
				t.close();
			}
		}
	}
}
