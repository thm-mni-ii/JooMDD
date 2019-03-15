package de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedReference
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla.JoomlaUtil.StaticLanguage

/**
 * This class contains the templates to generate the necessary code for frontend views.
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class DetailsPageTemplateFrontEndHelper {
	private ExtendedDynamicPage dpage
	private ExtendedComponent  com
	private String sec
	private ExtendedEntity mainEntity
	
	new(ExtendedDynamicPage dp, ExtendedComponent cp, String section) {
		dpage = dp
		com = cp
		sec = section
		mainEntity = dp.extendedEntityList.get(0)
	}
	
	def CharSequence generateSiteControllerSave()'''
	/**
	 * Method to save the data.
	 *
	 * @return  void
	 * @since   1.6
	 */
	public function save()
	{
	    // Check for request forgeries.
	    Session::checkToken() or jexit(Text::_('JINVALID_TOKEN'));

	    // Initialise variables.
	    $app = Factory::getApplication();
	    $model = $this->getModel('«dpage.name.toFirstUpper»Edit', '«com.name.toFirstUpper»Model');

	    // Get the user data.
	    $data = Factory::getApplication()->input->get('jform', array(), 'array');

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
	        $id = (int) $app->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».«mainEntity.primaryKey.name»');
	        $this->setRedirect(
	            Route::_(
	                'index.php?option=com_«com.name.toLowerCase»&view=«dpage.name.toLowerCase»edit&layout=edit&«mainEntity.primaryKey.name»=' . $id
	            )
	        );
	
	        return false;
	    }
	
	    // Attempt to save the data.
	    $return = $model->save($data);
	
	    // Check for errors.
	    if ($return === false) {
	        // Save the data in the session.
	        $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».data', $data);
	
	        // Redirect back to the edit screen.
	        $id = (int) $app->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».«mainEntity.primaryKey.name»');
	        $this->setMessage(Text::sprintf('Save failed', $model->getError()), 'warning');
	        $this->setRedirect(
	            Route::_(
	                'index.php?option=com_«com.name.toLowerCase»&view=«dpage.name.toLowerCase»edit&layout=edit&«mainEntity.primaryKey.name»=' . $id,
	                false
	            )
	        );
	
	        return false;
	    }
	
	
	    // Check in the profile.
	    if ($return) {
	        $model->checkin($return);
	    }
	
	    // Clear the profile id from the session.
	    $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».«mainEntity.primaryKey.name»', null);
	
	    // Redirect to the list screen.
	    $this->setMessage(Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name), StaticLanguage.ITEM_SAVED_SUCCESSFULLY)»'));
	    $menu = Factory::getApplication()->getMenu();
	    $item = $menu->getActive();
	    $url = (empty($item->link) ? 'index.php?' : $item->link);
	    $this->setRedirect(
	        Route::_(
	            $url,
	            false
	        )
	    );
	
	    // Flush the data from the session.
	    $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».data', null);
	}
	'''
	
	def CharSequence generateSiteControllerCancel()'''
	/**
	 * Handles the cancel action.
	 */
	public function cancel()
	{
	    $app = Factory::getApplication();
	
	    // Get the current edit id.
	    $editId = (int) $app->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».«mainEntity.primaryKey.name»');
	
	    // Get the model.
	    $model = $this->getModel('«dpage.name.toFirstUpper»Edit', '«com.name.toFirstUpper»Model');

	    // Check in the item
	    if ($editId) {
	        $model->checkin($editId);
	    }
	
	    $menu = Factory::getApplication()->getMenu();
	    $item = $menu->getActive();
	    $url = (empty($item->link) ? 'index.php?' : $item->link);
	    $this->setRedirect(Route::_($url, false));
	}
	'''
	
	def CharSequence generateSiteControllerRemove()'''
	/**
	 * Delete an item
	 */
	public function remove()
	{
	    // Initialise variables.
	    $app = Factory::getApplication();
	
	    //Checking if the user can remove object
	    $user = Factory::getUser();
	    if ($user->authorise('core.delete', 'com_«com.name.toLowerCase»')) {
	        $model = $this->getModel('«dpage.name.toFirstUpper»', '«com.name.toFirstUpper»Model');
	
	        // Get the user data.
	        $id = $app->input->getInt('«mainEntity.primaryKey.name»', 0);
	
	        // Attempt to delete the data.
	        $return = $model->delete($id);

	        // Check for errors.
	        if ($return === false) {
	            $this->setMessage(Text::sprintf('Delete failed', $model->getError()), 'warning');
	        } else {
	            // Check in the profile.
	            if ($return) {
	                $model->checkin($return);
	            }

	            // Clear the profile id from the session.
	            $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».«mainEntity.primaryKey.name»', null);

	            // Flush the data from the session.
	            $app->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».data', null);

	            $this->setMessage(Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name), StaticLanguage.ITEM_DELETED_SUCCESSFULLY)»'));
	        }

	        // Redirect to the list screen.
	        $menu = &JSite::getMenu();
	        $item = $menu->getActive();
	        $this->setRedirect(Route::_($item->link, false));
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
	 * @since  1.6
	 */
	protected function populateState()
	{
	    $app = Factory::getApplication('com_«com.name.toLowerCase»');
	
	    // Load state from the request userState on edit or from the passed variable on default
	    if (Factory::getApplication()->input->get('layout') == 'edit') {
	        $id = Factory::getApplication()->getUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».«mainEntity.primaryKey.name»');
	    } else {
	        $id = Factory::getApplication()->input->get('«mainEntity.primaryKey.name»');
	        Factory::getApplication()->setUserState('com_«com.name.toLowerCase».edit.«dpage.name.toLowerCase».«mainEntity.primaryKey.name»', $id);
	    }
	    $this->setState($this->getName().'.«mainEntity.primaryKey.name»', $id);
	
	    // Load the parameters.
	    $params = $app->getParams();
	    $params_array = $params->toArray();
	    if (isset($params_array['item_id'])) {
	        $this->setState($this->getName().'.«mainEntity.primaryKey.name»', $params_array['item_id']);
	    }
	    $this->setState('params', $params);
	}
	'''
	
	def CharSequence generateSiteModelgetTable()'''
	public function getTable($type = '«dpage.entities.get(0).name.toFirstUpper»', $prefix = '«com.name.toFirstUpper»Table', $config = array())
	{
	    $this->addTablePath(JPATH_COMPONENT_ADMINISTRATOR . '/tables');
	    return Table::getInstance($type, $prefix, $config);
	}
	'''
	
	def CharSequence generateSiteModelCheckin()'''
	/**
	 * Method to check in an item.
	 *
	 * @param  integer  The id of the row to check out.
	 * @return  boolean  True on success, false on failure.
	 * @since 1.6
	 */
	public function checkin($id = null)
	{
	    // Get the id.
	    $id = (!empty($id)) ? $id : (int) $this->getState($this->getName() . '.«mainEntity.primaryKey.name»');

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
	 * @param   integer  The id of the row to check out.
	 * @return  boolean  True on success, false on failure.
	 * @since   1.6
	 */
	public function checkout($id = null)
	{
	    // Get the user id.
	    $id = (!empty($id)) ? $id : (int) $this->getState($this->getName() . '.«mainEntity.primaryKey.name»');
	
	    if ($id) {
	        // Initialise the table
	        $table = $this->getTable();
	
	        // Get the current user object.
	        $user = Factory::getUser();
	
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
	 * @param   array  The form data.
	 * @return  mixed  The user id on success, false on failure.
	 * @since   1.6
	 */
	public function save($data)
	{
	    $id = (!empty($data['«mainEntity.primaryKey.name»'])) ? $data['«mainEntity.primaryKey.name»'] : (int)$this->getState($this->getName() . '.«mainEntity.primaryKey.name»');
	    $state = (!empty($data['state'])) ? 1 : 0;
	    $user = Factory::getUser();
	
	    if ($id) {
	        //Check the user can edit this item
	        $authorised = $user->authorise('core.edit', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».' . $id)
	            || $authorised = $user->authorise('core.edit.own', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».' . $id);
	        if ($user->authorise('core.edit.state', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id) !== true && $state == 1) {
	            //The user cannot edit the state of the item.
	            $data['state'] = 0;
	        }
	    } else {
	        //Check the user can create new items in this section
	        $authorised = $user->authorise('core.create', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
	        if ($user->authorise('core.edit.state', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id) !== true && $state == 1) {
	            //The user cannot edit the state of the item.
	            $data['state'] = 0;
	        }
	    }
	
	    if ($authorised !== true) {
	        JError::raiseError(403, Text::_('JERROR_ALERTNOAUTHOR'));
	        return false;
	    }
	    $inputs = Factory::getApplication()->input->get("jform", array(), 'array');
	
	    $table = $this->getTable();
	    if ($table->save($data) === true) {
	        if (empty($inputs['«mainEntity.primaryKey.name»']) || $inputs['«mainEntity.primaryKey.name»'] == 0) {
	            $inputs['patid']=$table->«mainEntity.primaryKey.name»;
	        }
	        «FOR ExtendedReference ref: dpage.extendedEntityList.get(0).allExtendedReferences»
	        «IF ref.upper.equalsIgnoreCase("*") || ref.upper.equalsIgnoreCase("-1")»
	        $this->set«ref.entity.name»($inputs);
	        «ENDIF»
	        «ENDFOR»
	        return $table->«mainEntity.primaryKey.name»;
	    } else {
	        return false;
	    }
	}
	'''
	
	def CharSequence generateSiteModelDelete()'''
	/**
	 * Delete data of an item
	 * @param  Int  $datacontent  the Id
	 *
	 * @return Int or False
	 */
	public function delete($data)
	{
	    $id = (!empty($data)) ? $data : (int)$this->getState($this->getName() . '.«mainEntity.primaryKey.name»');
	    if (Factory::getUser()->authorise('core.delete', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$id) !== true) {
	        JError::raiseError(403, Text::_('JERROR_ALERTNOAUTHOR'));
	        return false;
	    }
	    $table = $this->getTable();
	    if ($table->delete($data) === true) {
	        return $id;
	    } else {
	        return false;
	    }
	}
	'''
	
	def CharSequence generateSiteModelgetCategory()'''
	/**
	 * to search the Category name
	 * @param  Int  $id  content the Id
	 *
	 * @return  mixed  The return value or null if the query failed.
	 */
	public function getCategoryName($id)
	{
	    $db = Factory::getDbo();
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
	 * @param  Int  $id  content the Id
	 * @param  Int  $state
	 *
	 * @return  boolean  True if successful
	 */
	public function publish($id, $state)
	{
	    $table = $this->getTable();
	    $table->load($id);
	    $table->state = $state;
	    return $table->store();
	}
	'''
	
	def CharSequence generateSiteViewDisplay(Boolean isedit,String editName)'''
	/**
	 * Display the view
	 */
	public function display($tpl = null)
	{
	    $app = Factory::getApplication();
	    $user = Factory::getUser();
	
	    $this->state = $this->get('State');
	    $this->item = $this->get('Item');
	    $this->params = $app->getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
	
	    «IF isedit» 
	    $this->setLayout('Edit');
	    $this->form = $this->get('Form');
	    if (empty($this->item->«mainEntity.primaryKey.name»)) {
	        $authorised = $user->authorise('core.create', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
	    } else {
	        $authorised = $user->authorise('core.edit', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
	    } if ($authorised !== true) {
	        $app->enqueueMessage(Text::_('JERROR_ALERTNOAUTHOR'), 'error');
	        $app->setHeader('status', 403, true);

	        return false;
	    }
	    «ELSE»
	    if (!empty($this->item)) {
	        $this->form = $this->get('Form');
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
	            throw new Exception(Text::_('JERROR_ALERTNOAUTHOR'));
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
	 protected function _prepareDocument()
	 {
	     $app = Factory::getApplication();
	     $menus = $app->getMenu();
	     $title = null;
	 
	     // Because the application sets a default page title,
	     // we need to get it from the menu item itself
	     $menu = $menus->getActive();
	     if ($menu) {
	         $this->params->def('page_heading', $this->params->get('page_title', $menu->title));
	     } else {
	         $this->params->def('page_heading', Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, dpage.name, "DEFAULT", "PAGE", "TITLE"), dpage.name)»'));
	     }
	     $title = $this->params->get('page_title', '');
	     if (empty($title)) {
	         $title = $app->get('sitename');
	     } elseif ($app->get('sitename_pagetitles', 0) == 1) {
	         $title = Text::sprintf('JPAGETITLE', $app->get('sitename'), $title);
	     } elseif ($app->get('sitename_pagetitles', 0) == 2) {
	         $title = Text::sprintf('JPAGETITLE', $title, $app->get('sitename'));
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

	def generateSiteViewLayoutEditForm(String editPageName) '''
	<form 
	    id="form-«dpage.name.toLowerCase»"
	    action="<?php echo Route::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«editPageName.toLowerCase».save'); ?>"
	    method="post"
	    class="form-validate form-horizontal"
	    enctype="multipart/form-data"
	>
	    <div class="control-group">
	        <div class="controls">
	            <button type="submit" class="validate btn btn-primary"><?php echo Text::_('JSUBMIT'); ?></button>
	            <a
	                class="btn"
	                href="<?php echo Route::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«editPageName.toLowerCase».cancel'); ?>"
	                title="<?php echo Text::_('JCANCEL'); ?>">
	            <?php echo Text::_('JCANCEL'); ?>
	            </a>
	        </div>
	    </div>
	    <div class="form-horizontal">
	        <?php echo HTMLHelper::_('bootstrap.startTabSet', 'myTab', array('active' => 'general')); ?>
	        <?php echo HTMLHelper::_(
	            'bootstrap.addTab',
	            'myTab',
	            'general',
	            Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "TITLE", dpage.name), dpage.name)»', true)
	        ); ?>
	        <div class="row-fluid">
	            <div class="span10 form-horizontal">
	                <fieldset class="adminform">
	                    <?php echo $this->form->getInput('«mainEntity.primaryKey.name»'); ?>
	                    <?php echo $this->form->getInput('ordering'); ?>
	                    <input type="hidden" name="jform[state]" value="<?php echo $this->item->state; ?>" />
	                    «IF !dpage.extendedEditedFieldsList.isNullOrEmpty && (dpage.extendedEditedFieldsList.filter[t | t.extendedAttribute.name.equalsIgnoreCase("title")]).size == 0»
	                    <input type="hidden" id="jform[title]" value="<?php echo $this->item->«dpage.extendedEditedFieldsList.get(0).attribute.name»; ?>" />
	                    «ENDIF»
	                    <input
	                        type="hidden"
	                        name="jform[checked_out]"
	                        value="<?php echo isset($this->item->checked_out) ?
	                            $this->item->checked_out :
	                            Factory::getUser()->id; ?>"
	                    />
	                    <input
	                        type="hidden"
	                        name="jform[checked_out_time]"
	                        value="<?php echo isset($this->item->checked_out_time) ?
	                            $this->item->checked_out_time :
	                            date("Y-m-d H:i:s"); ?>"
	                    />
	                    <?php if (empty($this->item->created_by)) : ?>
	                    <input
	                        type="hidden"
	                        name="jform[created_by]"
	                        value="<?php echo Factory::getUser()->id; ?>"
	                    />
	                    <?php else : ?>
	                    <input
	                        type="hidden"
	                        name="jform[created_by]"
	                        value="<?php echo $this->item->created_by; ?>"
	                    />
	                    <?php endif; ?>
	                    «Slug.generateEntytiesInputAttribute(dpage.extendedEditedFieldsList, dpage.extendedEntityList.get(0))»
	                </fieldset>
	            </div>
	        </div>
	        <?php echo HTMLHelper::_('bootstrap.endTab'); ?>
	        «FOR ExtendedReference ref: dpage.extendedEntityList.get(0).allExtendedReferences.filter[t | t.upper.equalsIgnoreCase("*") || t.upper.equalsIgnoreCase("-1")]»
	        «Slug.generateEntytiesSiteInputRefrence(ref,com)»
	        «ENDFOR»
	        <?php if (Factory::getUser()->authorise('core.admin', '«com.name»')) : ?>
	            <?php echo HTMLHelper::_(
	                'bootstrap.addTab',
	                'myTab',
	                'permissions',
	                Text::_('JGLOBAL_ACTION_PERMISSIONS_LABEL', true)
	            ); ?>
	            <?php echo $this->form->getInput('rules'); ?>
	            <?php echo HTMLHelper::_('bootstrap.endTab'); ?>
	        <?php endif; ?>
	        <?php echo HTMLHelper::_('bootstrap.endTabSet'); ?>
	        <input type="hidden" name="option" value="«Slug.nameExtensionBind("com",com.name).toLowerCase»" />
	        <input type="hidden" name="task" value="«editPageName».save" />
	        <?php echo HTMLHelper::_('form.token'); ?>
	    </form>
	</div>
	'''
	
	def generateSiteViewLayoutShow(String editPageName) '''
	<?php if ($this->item ) : ?>

	    <div class="item_fields">
	        <table class="table">
	            <tr>
	                <th><?php echo Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "FORM", "LBL", "NONE"), StaticLanguage.STATE)»'); ?></th>
	                <td>
	                    <i class="icon-<?php echo ($this->item->state == 1) ? 'publish' : 'unpublish'; ?>"></i>
	                </td>
	            </tr>
	            <tr>
	                <th><?php echo Text::_('JGLOBAL_FIELD_CREATED_BY_LABEL'); ?></th>
	                <td><?php echo Factory::getUser($this->item->created_by)->name; ?></td>
	            </tr>
	            «FOR ExtendedAttribute a: dpage.extendedTableColumnList.filter[t | !t.isprimary]»
	            «attributShowTemplate(a, dpage.entities.get(0) )»
	            «ENDFOR»
	        </table>
	    </div>
	
	    <?php if ($canEdit && $this->item->checked_out == 0) : ?>
	    <a
	        class="btn btn-primary"
	        href="<?php echo Route::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&view=«editPageName.toLowerCase»&«mainEntity.primaryKey.name»='.$this->item->«mainEntity.primaryKey.name»); ?>"
	    >
	    <?php echo Text::_("JACTION_EDIT"); ?></a>
	    <?php endif; ?>
	    <?php if (Factory::getUser()->authorise('core.delete', '«Slug.nameExtensionBind("com", com.name).toLowerCase».«dpage.name.toLowerCase».'.$this->item->«mainEntity.primaryKey.name»)) : ?>
	    <a
	        class="btn btn-danger"
	        href="<?php echo Route::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«editPageName.toLowerCase».remove&«mainEntity.primaryKey.name»=' . $this->item->«mainEntity.primaryKey.name»); ?>"
	    >
	    <?php echo Text::_("JACTION_DELETE"); ?></a>
	    <?php endif; ?>
	<?php else :
	    echo Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name), StaticLanguage.ITEM_NOT_LOADED)»');
	endif;
	?>
	'''
	
	def  CharSequence attributShowTemplate(ExtendedAttribute attr, Entity e) '''
	«switch (attr.htmlType.toLowerCase) {
		case "image": {
		'''
		<tr>
		    <th><?php echo Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "FORM", "LBL", e.name, attr.name), attr.name)»»'); ?></th>
		    <td>
		        <img id='<?php  echo $this->item->«attr.name.toLowerCase»; ?>' name= '<?php  echo $this->item->«attr.name.toLowerCase»; ?>'src='<?php echo $image_path . '/'. $this->item->«attr.name.toLowerCase»; ?>' />
		    </td>
		</tr>'''
		}
		case "file": {
		'''
		<tr>
		    <th><?php echo Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "FORM", "LBL", e.name, attr.name), attr.name)»»'); ?></th>
		    <td>
		        <a id='<?php  echo $this->item->«attr.name.toLowerCase»; ?>' name= '<?php  echo $this->item->«attr.name.toLowerCase»; ?>' href="<?php echo $file_path . '/'. $this->item->«attr.name.toLowerCase»; ?>">
		            <img src='<?php echo $iconpath . '/'. explode('.',$this->item->«attr.name.toLowerCase»)[0]; ?>' />'<?php  echo $this->item->«attr.name.toLowerCase»; ?>'
		        </a>
		    </td>
		</tr>'''
		}
		case "link": {
		'''
		<tr>
		    <th><?php echo Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "FORM", "LBL", e.name, attr.name), attr.name)»'); ?></th>
		    <td>
		        <a href="<?php echo $this->item->«attr.name.toLowerCase»; ?>">
		            <?php echo $this->item->«attr.name.toLowerCase»; ?>
		        </a>
		    </td>
		</tr>'''
		}
		default: {
		'''
		<tr>
		    <th>
		        <?php echo Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "FORM", "LBL", e.name, attr.name), attr.name)»'); ?>
		    </th>
		    <td>
		        <?php echo $this->item->«attr.name.toLowerCase»; ?>
		    </td>
		</tr>'''
		}
	} »
	'''	
}