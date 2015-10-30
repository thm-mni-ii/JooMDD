package de.thm.icampus.ejsl.generator.pages

import de.thm.icampus.ejsl.eJSL.DetailsPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.Attribute
import de.thm.icampus.ejsl.generator.util.Slug
import de.thm.icampus.ejsl.eJSL.Entity

class DetailsPageTemplateFrontEndHelper {
	private DetailsPage dpage
	private Component  com
	private String sec
	
	new(DetailsPage dp, Component cp, String section){
		
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
		        $model = $this->getModel('«dpage.name.toFirstUpper»Edit', '«com.name.toFirstUpper»Model');
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
		        $model = $this->getModel('«dpage.name.toFirstUpper»Edit', '«com.name.toFirstUpper»Model');
	
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
		       $model = $this->getModel('«dpage.name.toFirstUpper»Edit', '«com.name.toFirstUpper»Model');

		
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
		
		            // Attempt to delete the data.
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
	public function getTable($type = '«dpage.entities.get(0).name.toFirstUpper»', $prefix = '«com.name.toFirstUpper»Table', $config = array()) {
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
	        $id = (!empty($id)) ? $id : (int) $this->getState('«dpage.name.toLowerCase».id');
	 
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
	def CharSequence generateSiteModelCheckout()'''
	/**
	     * Method to check out an item for editing.
	     *
	     * @param	integer		The id of the row to check out.
	     * @return	boolean		True on success, false on failure.
	     * @since	1.6
	*/
	    public function checkout($id = null) {
	        // Get the user id.
	        $id = (!empty($id)) ? $id : (int) $this->getState('«dpage.name.toLowerCase».id');
	
	        if ($id) {
	
	            // Initialise the table
	            $table = $this->getTable();
	
	            // Get the current user object.
	            $user = JFactory::getUser();
	
	            // Attempt to check the row out.
	            if (method_exists($table, 'checkout')) {
	                if (!$table->checkout($user->get('id'), $id)) {
	                    $this->setError($table->getError());
	                    return false;
	                }
	            }
	        }

	        return true;
	    }
	'''
	def CharSequence generateSiteModelSave()'''
	/**
	 * Method to save the form data.
	 *
	 * @param	array		The form data.
	 * @return	mixed		The user id on success, false on failure.
	 * @since	1.6
	 */
		public function save($data)
		{
			$id = (!empty($data['id'])) ? $data['id'] : (int)$this->getState('«dpage.name.toLowerCase».id');
	        $state = (!empty($data['state'])) ? 1 : 0;
	        $user = JFactory::getUser();
	
	        if($id) {
	            //Check the user can edit this item
	            $authorised = $user->authorise('core.edit', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id) 
	            || $authorised = $user->authorise('core.edit.own', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id);
	            if($user->authorise('core.edit.state', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id) !== true && $state == 1){ //The user cannot edit the state of the item.
	                $data['state'] = 0;
	            }
	        } else {
	            //Check the user can create new items in this section
	            $authorised = $user->authorise('core.create', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
	            if($user->authorise('core.edit.state', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id) !== true && $state == 1){ //The user cannot edit the state of the item.
	                $data['state'] = 0;
	            }
	        }
	
	        if ($authorised !== true) {
	            JError::raiseError(403, JText::_('JERROR_ALERTNOAUTHOR'));
	            return false;
	        }
	        
	        $table = $this->getTable();
	        if ($table->save($data) === true) {
	            return $table->id;
	        } else {
	            return false;
	        }
	        
		}
	
	'''
	def CharSequence generateSiteModelDelete()'''
	/**
	* to Delete Data of a Item
	*@param Int $data   content the Id
	*
	*/
		 function delete($data)
	    {
	        $id = (!empty($data)) ? $data : (int)$this->getState('«dpage.name.toLowerCase».id');
	        if(JFactory::getUser()->authorise('core.delete', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id) !== true){
	            JError::raiseError(403, JText::_('JERROR_ALERTNOAUTHOR'));
	            return false;
	        }
	        $table = $this->getTable();
	        if ($table->delete($data) === true) {
	            return $id;
	        } else {
	            return false;
	        }
	        
	        return true;
	    }
	'''
	def CharSequence generateSiteModelgetCategory()'''
	/**
	* to search the Category name
	*@param Int $id   content the Id
	*
	*/
	public function getCategoryName($id) {
	    $db = JFactory::getDbo();
	    $query = $db->getQuery(true);
	    $query
	            ->select('title')
	            ->from('#__categories')
	            ->where('id = ' . $id);
	    $db->setQuery($query);
	    return $db->loadObject();
	}
	'''
	def CharSequence generateSiteModelpublish()'''
	/**
	* check, if the item published
	*@param Int $id   content the Id
	*@param Int $state
	*
	*/ 
	public function publish($id, $state) {
	    $table = $this->getTable();
	    $table->load($id);
	    $table->state = $state;
	    return $table->store();
	}
	'''
	def CharSequence generateSiteViewDisplay(Boolean isedit)'''
	/**
	* Display the view
	*/
	    public function display($tpl = null) {
			
	        $app = JFactory::getApplication();
	        $user = JFactory::getUser();
	
	        $this->state = $this->get('State');
	        $this->item = $this->get('Item');
	        $this->params = $app->getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
			«IF isedit»
			$this->setLayout('Edit');
			$this->form		= $this->get('Form');
			«ELSE»
			if (!empty($this->item)) {
			
			$this->form		= $this->get('Form');
			}
			«ENDIF»
	
	        // Check for errors.
	        if (count($errors = $this->get('Errors'))) {
	            throw new Exception(implode("\n", $errors));
	        }
	
	        
			«IF !isedit»
			if ($this->_layout == 'edit') {
			
			$authorised = $user->authorise('core.create', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
			
			if ($authorised !== true) {
			throw new Exception(JText::_('JERROR_ALERTNOAUTHOR'));
			}
			}
			«ENDIF»
	        $this->_prepareDocument();
	
	        parent::display($tpl);
	}
	'''
	def CharSequence generateSiteViewprepareDocument()'''
	 /**
	 * Prepares the document
	 */
	    protected function _prepareDocument() {
	        $app = JFactory::getApplication();
	        $menus = $app->getMenu();
	        $title = null;
	 
	        // Because the application sets a default page title,
	        // we need to get it from the menu item itself
	        $menu = $menus->getActive();
	        if ($menu) {
	            $this->params->def('page_heading', $this->params->get('page_title', $menu->title));
	        } else {
	            $this->params->def('page_heading', JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_DEFAULT_PAGE_TITLE'));
	        }
	        $title = $this->params->get('page_title', '');
	        if (empty($title)) {
	            $title = $app->getCfg('sitename');
	        } elseif ($app->getCfg('sitename_pagetitles', 0) == 1) {
	            $title = JText::sprintf('JPAGETITLE', $app->getCfg('sitename'), $title);
	        } elseif ($app->getCfg('sitename_pagetitles', 0) == 2) {
	            $title = JText::sprintf('JPAGETITLE', $title, $app->getCfg('sitename'));
	        }
	        $this->document->setTitle($title);
	 
	        if ($this->params->get('menu-meta_description')) {
	            $this->document->setDescription($this->params->get('menu-meta_description'));
	        }
	 
	        if ($this->params->get('menu-meta_keywords')) {
	            $this->document->setMetadata('keywords', $this->params->get('menu-meta_keywords'));
	        }
	 
	        if ($this->params->get('robots')) {
	            $this->document->setMetadata('robots', $this->params->get('robots'));
	        }
	    }
	 
	'''
	
	def generateSiteViewLayoutEditForm() '''
	<form id="form-«dpage.name.toLowerCase»" action="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«dpage.name.toLowerCase»edit.save'); ?>" method="post" class="form-validate form-horizontal" enctype="multipart/form-data">
				<input type="hidden" name="jform[id]" value="<?php echo $this->item->id; ?>" />
				<input type="hidden" name="jform[ordering]" value="<?php echo $this->item->ordering; ?>" />
				<input type="hidden" name="jform[state]" value="<?php echo $this->item->state; ?>" />
				<input type="hidden" name="jform[checked_out]" value="<?php if(isset($this->item->checked_out)){
				 echo $this->item->checked_out;}else{ echo JFactory::getUser()->id;} ?>" />
				<input type="hidden" name="jform[checked_out_time]" value="<?php if(isset($this->item->checked_out_time)){
				 echo $this->item->checked_out_time;}else{ echo date("Y-m-d H:i:s") ;} ?>" />
	«Slug.generateEntytiesHiddenAttribute(dpage.entities.get(0),dpage.entities)»

	<?php if(empty($this->item->created_by)): ?>
		<input type="hidden" name="jform[created_by]" value="<?php echo JFactory::getUser()->id; ?>" />
	<?php else: ?>
		<input type="hidden" name="jform[created_by]" value="<?php echo $this->item->created_by; ?>" />
	<?php endif; ?>
	«Slug.generateEntytiesInputAttribute(dpage.entities.get(0), dpage.entities)»
	<div class="fltlft" <?php if (!JFactory::getUser()->authorise('core.admin','«com.name.toLowerCase»')): ?> style="display:none;" <?php endif; ?> >
	                <?php echo JHtml::_('sliders.start', 'permissions-sliders-'.$this->item->id, array('useCookie'=>1)); ?>
	                <?php echo JHtml::_('sliders.panel', JText::_('ACL Configuration'), 'access-rules'); ?>
	                <fieldset class="panelform">
	                    <?php echo $this->form->getLabel('rules'); ?>
	                    <?php echo $this->form->getInput('rules'); ?>
	                </fieldset>
	                <?php echo JHtml::_('sliders.end'); ?>
	            </div>
					<?php if (!JFactory::getUser()->authorise('core.admin','«com.name.toLowerCase»')): ?>
	                <script type="text/javascript">
	                    jQuery.noConflict();
	                    jQuery('.tab-pane select').each(function(){
	                       var option_selected = jQuery(this).find(':selected');
	                       var input = document.createElement("input");
	                       input.setAttribute("type", "hidden");
	                       input.setAttribute("name", jQuery(this).attr('name'));
	                       input.setAttribute("value", option_selected.val());
	                       document.getElementById("form-«dpage.name.toLowerCase»").appendChild(input);
	                    });
	                </script>
	             <?php endif; ?>
	        <div class="control-group">
	            <div class="controls">
	                <button type="submit" class="validate btn btn-primary"><?php echo JText::_('JSUBMIT'); ?></button>
	                <a class="btn" href="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«dpage.name.toLowerCase»edit.cancel'); ?>" title="<?php echo JText::_('JCANCEL'); ?>"><?php echo JText::_('JCANCEL'); ?></a>
	            </div>
	        </div>
	        
	        <input type="hidden" name="option" value="«Slug.nameExtensionBind("com", com.name).toLowerCase»" />
	        <input type="hidden" name="task" value="«dpage.name.toLowerCase»edit.save" />
	        <?php echo JHtml::_('form.token'); ?>
	    </form>
</div>
	'''
	def generateSiteViewLayoutShow() '''
	<?php if ($this->item ) : ?>

	<div class="item_fields">
	<table class="table">
	 <tr>
			<th><?php echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_ID'); ?></th>
			<td><?php echo $this->item->id; ?></td>
	</tr>
	<tr>
			<th><?php echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_STATE'); ?></th>
			<td>
			<i class="icon-<?php echo ($this->item->state == 1) ? 'publish' : 'unpublish'; ?>"></i></td>
	</tr>
	<tr>
			<th><?php echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_NONE_CREATED_BY'); ?></th>
			<td><?php echo $this->item->created_by; ?></td>
	</tr>
	«FOR Attribute a: dpage.entities.get(0).attributes»
	«attributShowTemplate(a, dpage.entities.get(0) )»
	«ENDFOR»
	</table>
	</div>
	<?php if($canEdit && $this->item->checked_out == 0): ?>
		<a class="btn" href="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«dpage.name.toLowerCase»edit.edit&id='.$this->item->id); ?>">
		<?php echo JText::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_EDIT_ITEM"); ?></a>
	<?php endif; ?>
	<?php if(JFactory::getUser()->authorise('core.delete','«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$this->item->id)):?>
		<a class="btn" href="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«dpage.name.toLowerCase»edit.remove&id=' . $this->item->id, false, 2); ?>">
		<?php echo JText::_("«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE_ITEM"); ?></a>
	<?php endif; ?>
	<?php
else:
	echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_ITEM_NOT_LOADED');
endif;
?>
	'''
	def  CharSequence attributShowTemplate(Attribute attr, Entity e) '''
	<tr>
			<th><?php echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_«e.name.toUpperCase»_«attr.name.toUpperCase»'); ?></th>
			<td><?php echo $this->item->«attr.name.toLowerCase»; ?></td></tr>
	'''
	
	
	
}