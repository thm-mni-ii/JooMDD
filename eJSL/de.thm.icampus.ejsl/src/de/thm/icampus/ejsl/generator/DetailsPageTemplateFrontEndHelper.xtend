package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section

class DetailsPageTemplateFrontEndHelper {
	private DetailsPage dpage
	private Component  com
	private Section sec
	
	new(DetailsPage dp, Component cp, Section section){
		
		dpage = dp
		com = cp
		sec = section
	}
	
	def CharSequence generateSiteControllerEdit(Boolean isedit)'''
		/**
		* Method to check out an item for editing and redirect to the edit form.
		*
		* @since	1.6
		*/
		    public function edit() {
		        $app = JFactory::getApplication();
		
		        // Get the previous edit id (if any) and the current edit id.
		        $previousId = (int) $app->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id');
		        $editId = JFactory::getApplication()->input->getInt('id', null, 'array');
		
		        // Set the user id for the user to edit in the session.
		        $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id', $editId);
			
		        // Get the model.
		        «IF isedit»
		        $model = $this->getModel('«dpage.name.toFirstUpper»'Edit, '«com.name.toFirstUpper»Model');
		        «ELSE»
		        $model = $this->getModel('«dpage.name.toFirstUpper»', '«com.name.toFirstUpper»Model');
		        «ENDIF»
		
		        // Check out the item
		        if ($editId) {
		            $model->checkout($editId);
		        }
		
		        // Check in the previous user.
		        if ($previousId && $previousId !== $editId) {
		            $model->checkin($previousId);
		        }
		
		        // Redirect to the edit screen.
		        $this->setRedirect(JRoute::_('index.php?option=com_«com.name.toLowerCase»&view=«dpage.name.toLowerCase»«if(isedit) "edit"»&layout=edit', false));
		    }
	'''
	def CharSequence generateSiteControllerSave()'''
	/**
	* Method to save the data.
	*
	* @return	void
	* @since	1.6
	*/
	    public function save() {
	        // Check for request forgeries.
	        JSession::checkToken() or jexit(JText::_('JINVALID_TOKEN'));
	
	        // Initialise variables.
	        $app = JFactory::getApplication();
		        $model = $this->getModel('«dpage.name.toFirstUpper»'Edit, '«com.name.toFirstUpper»Model');
	
	        // Get the user data.
	        $data = JFactory::getApplication()->input->get('jform', array(), 'array');
	
	        // Validate the posted data.
	        $form = $model->getForm();
	        if (!$form) {
	            JError::raiseError(500, $model->getError());
	            return false;
	        }
	
	        // Validate the posted data.
	        $data = $model->validate($form, $data);
	
	        // Check for errors.
	        if ($data === false) {
	            // Get the validation messages.
	            $errors = $model->getErrors();
	
	            // Push up to three validation messages out to the user.
	            for ($i = 0, $n = count($errors); $i < $n && $i < 3; $i++) {
	                if ($errors[$i] instanceof Exception) {
	                    $app->enqueueMessage($errors[$i]->getMessage(), 'warning');
	                } else {
	                    $app->enqueueMessage($errors[$i], 'warning');
	                }
	            }
	
	            $input = $app->input;
	            $jform = $input->get('jform', array(), 'ARRAY');
	
	            // Save the data in the session.
	            $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».data', $jform, array());
	
	            // Redirect back to the edit screen.
	            $id = (int) $app->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id');
	            $this->setRedirect(JRoute::_('index.php?option=com_«com.name.toLowerCase»&view=«dpage.name.toLowerCase»edit&layout=edit&id=' . $id, false));
	            return false;
	        }
	
	        // Attempt to save the data.
	        $return = $model->save($data);
	
	        // Check for errors.
	        if ($return === false) {
	            // Save the data in the session.
	            $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».data', $data);
	
	            // Redirect back to the edit screen.
	            $id = (int) $app->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id');
	            $this->setMessage(JText::sprintf('Save failed', $model->getError()), 'warning');
	            $this->setRedirect(JRoute::_('index.php?option=com_«com.name.toLowerCase»&view=«dpage.name.toLowerCase»edit&layout=edit&id=' . $id, false));
	            return false;
	        }
	
	
	        // Check in the profile.
	        if ($return) {
	            $model->checkin($return);
	        }
	
	        // Clear the profile id from the session.
	        $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id', null);
	
	        // Redirect to the list screen.
	        $this->setMessage(JText::_('COM_«com.name.toUpperCase»_ITEM_SAVED_SUCCESSFULLY'));
	        $menu = JFactory::getApplication()->getMenu();
	        $item = $menu->getActive();
	        $url = (empty($item->link) ? 'index.php?' : $item->link);
	        $this->setRedirect(JRoute::_($url, false));
	
	        // Flush the data from the session.
	        $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».data', null);
	    }
	
	'''
	def CharSequence generateSiteControllerCancel()'''
	/**
	* To cancel the Edit of a Item
	*
	*/
		function cancel() {
		
		        $app = JFactory::getApplication();
		
		        // Get the current edit id.
		        $editId = (int) $app->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id');
		
		        // Get the model.
		       $model = $this->getModel('«dpage.name.toFirstUpper»'Edit, '«com.name.toFirstUpper»Model');

		
		        // Check in the item
		        if ($editId) {
		            $model->checkin($editId);
		        }
		        
		        $menu = JFactory::getApplication()->getMenu();
		        $item = $menu->getActive();
		        $url = (empty($item->link) ? 'index.php?' : $item->link);
		        $this->setRedirect(JRoute::_($url, false));
	    }
	'''
	
	def CharSequence generateSiteControllerRemove()'''
	/**
	*
	*Delete a Item
	*
	*/
		public function remove() {
		
		        // Initialise variables.
		        $app = JFactory::getApplication();
		
		        //Checking if the user can remove object
		        $user = JFactory::getUser();
		        if ($user->authorise($user->authorise('core.delete', 'com_«com.name.toLowerCase»'))) {
		            $model = $this->getModel('«dpage.name.toFirstUpper»', '«com.name.toFirstUpper»Model');
		
		            // Get the user data.
		            $id = $app->input->getInt('id', 0);
		
		            // Attempt to save the data.
		            $return = $model->delete($id);
		
		
		            // Check for errors.
		            if ($return === false) {
		                $this->setMessage(JText::sprintf('Delete failed', $model->getError()), 'warning');
		            } else {
		                // Check in the profile.
		                if ($return) {
		                    $model->checkin($return);
		                }
		
		                // Clear the profile id from the session.
		                $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id', null);
		
		                // Flush the data from the session.
		                $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».data', null);
		
		                $this->setMessage(JText::_('COM_«com.name.toUpperCase»_ITEM_DELETED_SUCCESSFULLY'));
		            }
		
		            // Redirect to the list screen.
		            $menu = & JSite::getMenu();
		            $item = $menu->getActive();
		            $this->setRedirect(JRoute::_($item->link, false));
		        } else {
		            throw new Exception(500);
		        }
		}
	'''
	def CharSequence generateSiteModelPopulatestate()'''
	/**
	* Method to auto-populate the model state.
	*
	* Note. Calling getState in this method will result in recursion.
	*
	* @since	1.6
	*/
	    protected function populateState() {
	        $app = JFactory::getApplication('com_«com.name.toLowerCase»');
	
	        // Load state from the request userState on edit or from the passed variable on default
	        if (JFactory::getApplication()->input->get('layout') == 'edit') {
	            $id = JFactory::getApplication()->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id');
	        } else {
	            $id = JFactory::getApplication()->input->get('id');
	            JFactory::getApplication()->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».id', $id);
	        }
	        $this->setState('«dpage.name.toLowerCase».id', $id);
	
	        // Load the parameters.
	        $params = $app->getParams();
	        $params_array = $params->toArray();
	        if (isset($params_array['item_id'])) {
	            $this->setState('«dpage.name.toLowerCase».id', $params_array['item_id']);
	        }
	        $this->setState('params', $params);
	}
	'''
	
	def CharSequence generateSiteModelgetData(Boolean isEdit)'''
/**
 * Method to get an ojbect.
 *
 * @param	integer	The id of the object to get.
 *
 * @return	mixed	Object on success, false on failure.
 */
	    public function &getData($id = null) {
	        if ($this->_item === null) {
	            $this->_item = false;
	
	            if (empty($id)) {
	                $id = $this->getState('«dpage.name.toLowerCase».id');
	            }
	
	            // Get a level row instance.
	            $table = $this->getTable();
	
	            // Attempt to load the row.
	            if ($table->load($id)) {
	            	«IF isEdit»
						$user = JFactory::getUser();
						$id = $table->id;
						if($id){
							$canEdit = $user->authorise('core.edit', 'com_«com.name.toLowerCase».«dpage.name.toLowerCase».'.$id) 
							|| $user->authorise('core.create', 'com_«com.name.toLowerCase».«dpage.name.toLowerCase».'.$id);
							}
						else{
							$canEdit = $user->authorise('core.edit', 'com_«com.name.toLowerCase»') || $user->authorise('core.create', 'com_«com.name.toLowerCase»');
							}
						if (!$canEdit && $user->authorise('core.edit.own', 'com_«com.name.toLowerCase».«dpage.name.toLowerCase».'.$id)) {
						$canEdit = $user->id == $table->created_by;
						}
						
						if (!$canEdit) {
						JError::raiseError('500', JText::_('JERROR_ALERTNOAUTHOR'));
						}
	            	«ENDIF»
	                // Check published state.
	                if ($published = $this->getState('filter.published')) {
	                    if ($table->state != $published) {
	                        return $this->_item;
	                    }
	                }
	
	                // Convert the JTable to a clean JObject.
	                $properties = $table->getProperties(1);
	                $this->_item = JArrayHelper::toObject($properties, 'JObject');
	            } elseif ($error = $table->getError()) {
	                $this->setError($error);
	            }
	        }
	
	        
			if ( isset($this->_item->created_by) ) {
				$this->_item->created_by_name = JFactory::getUser($this->_item->created_by)->name;
			}
			if ( isset($this->_item->user) ) {
				$this->_item->user_name = JFactory::getUser($this->_item->user)->name;
			}
	
	        return $this->_item;
	    }
	'''
	
	def CharSequence generateSiteModelgetTable()'''
	public function getTable($type = '«dpage.name.toFirstUpper»', $prefix = '«com.name.toFirstUpper»Table', $config = array()) {
	        $this->addTablePath(JPATH_COMPONENT_ADMINISTRATOR . '/tables');
	        return JTable::getInstance($type, $prefix, $config);
	    }
	'''
	def CharSequence generateSiteModelCheckin()'''
	 /**
	 * Method to check in an item.
	 *
	 * @param	integer		The id of the row to check out.
	 * @return	boolean		True on success, false on failure.
	 * @since	1.6
	 */
	    public function checkin($id = null) {
	        // Get the id.
	        $id = (!empty($id)) ? $id : (int) $this->getState('«dpage».id');
	 
	        if ($id) {
	 
	            // Initialise the table
	            $table = $this->getTable();
	 
	            // Attempt to check the row in.
	            if (method_exists($table, 'checkin')) {
	                if (!$table->checkin($id)) {
	                    $this->setError($table->getError());
	                    return false;
	                }
	            }
	        }
	 
	        return true;
	    }
	'''
	
	
}