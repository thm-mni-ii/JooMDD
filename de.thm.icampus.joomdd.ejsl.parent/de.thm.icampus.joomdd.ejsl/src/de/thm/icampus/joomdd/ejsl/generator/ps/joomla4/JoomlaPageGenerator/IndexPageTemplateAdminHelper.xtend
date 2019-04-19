package de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedEntity
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.Slug
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.Query
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.Select
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.DatabaseQuery.Column
import de.thm.icampus.joomdd.ejsl.generator.ps.joomla4.JoomlaUtil.StaticLanguage
import de.thm.icampus.joomdd.ejsl.generator.pi.util.MappingEntity

/**
 * This class contains the templates to generate the necessary code for backend view templates (index pages).
 * 
 * @author Dieudonne Timma, Dennis Priefer
 */
class IndexPageTemplateAdminHelper extends IndexPageTemplateHelper {

    ExtendedDynamicPage indexpage
    ExtendedComponent com
    String sec
    String details
    ExtendedEntity mainEntity

    new(ExtendedDynamicPage dp, ExtendedComponent cp, String section) {
        indexpage = dp
        com = cp
        sec = section
        var ExtendedDynamicPage dt = Slug.getPageForDetails(indexpage, com)
        details = if(dt === null) "<Put the name Of DetailsPage>" else dt.name
        mainEntity = dp.extendedEntityList.get(0)
    }

    def genAdminControllerGetModel() '''
        /**
         * Overwrite the  getModel.
         * @since 1.6
         */
        public function getModel($name = '«details.toFirstUpper»', $prefix = 'Administrator', $config = array())
        {
            $model = parent::getModel($name, $prefix, array('ignore_request' => true));
            return $model;
        }
    '''

    def CharSequence genAdminControllerSaveOrdering() '''
        /**
         * save the order.
         *
         * @return  Integer
         * @generated
         */
        public function saveordering()
        {
            $app = Factory::getApplication();
            $ids = $app->input->get('cid', array(), 'array');
            $model = $this->getModel('«indexpage.name.toLowerCase»');
            $result = $model->saveOrdering($ids);
            if ($result) {
                echo new JsonResponse($result);
            }
            
            return $result;
        }
    '''

    def CharSequence genAdminModelConstruct() '''
        /**
         * Constructor.
         *
         * @param    array    An optional associative array of configuration settings.
         * @see        JController
         * @since    1.6
         * @generated
         */
        public function __construct($config = array())
        {
            if (empty($config['filter_fields'])) {
                $config['filter_fields'] = array(
                    'ordering', '«indexpage.entities.get(0).name».ordering',
                    'state', '«indexpage.entities.get(0).name».state',
                    'created_by', '«indexpage.entities.get(0).name».created_by',
                    'published', '«indexpage.entities.get(0).name».published',
                    «indexpage.allAttributeOfFilterAndColum.map[ attr | 
    	                var column = indexpage.getTextColumn(attr, com.allExtendedEntity)
    	                ''''«attr.name»', '«column»'«»'''
    	            ].join(''',
    	            ''')»
                );
            }
            parent::__construct($config);
        }
    '''

    def CharSequence genAdminModelGetListQuery() {
        var query = new Query(com)
        var mainSelectColumn = new Column(indexpage.entities.get(0).name, '''*''')
        query.addToMainSelect(new Select(mainSelectColumn))

        return '''
            /**
             * Build an SQL query to load the list data.
             *
             * @return  JDatabaseQuery
             * @since 1.6
             * @generated
             */
            protected function getListQuery()
            {
                // Create a new query object.
                $db = $this->getDbo();
                $query = $db->getQuery(true);
                $published = $this->getState('filter.state');
                $created_by = $this->getState('filter.created_by');
                «FOR ExtendedAttribute attr : indexpage.extendFiltersList»
                    $«attr.name» = $this->getState('filter.«attr.name»');
                «ENDFOR»
                $search = $this->getState('filter.search');
                $orderCol = $this->state->get('list.ordering');
                $orderDirn = $this->state->get('list.direction');
            
                // Select the required fields from the table.
                $query->select(
                    "distinct " .
                    $this->getState(
                        'list.select',
                        '«query.mainSelect»'
                    )
                );
                «query.getListQuery(indexpage, mainEntity, '''<\/br>''', true)»
                
                return $query;
            }
           '''
    }

    def CharSequence genAdminModelGetItem() {
        var multiValueElementList = Slug.getMultiValueElements(this.indexpage)
        
        return '''
        /**
        * Method to get a single record.
        *
        * @param   integer  The id of the primary key.
        *
        * @return  mixed    Object on success, false on failure.
        * @since   1.6
        * @generated
        */
        public function getItems()
        {
            $items = parent::getItems();
            «IF multiValueElementList.size > 0»
            foreach ($items as $item) {
               «multiValueElementList.join('''
               
               ''')»
            }
            «ENDIF»
            return $items;
        }
        '''
    }

    def CharSequence genAdminModelSaveOrder() '''
        /**
         * Function to save the new Order of the Profile
         *
         * @param   Array  $dataID  content the ID in the new Ordering
         *
         * @return  array  including headers
         * @generated
         */
        public function saveOrdering($dataID)
        {
            $db = $this->getDbo();
        
            $statement = 'Update #__«com.name»_«indexpage.entities.get(0).name» Set `ordering` = CASE';

            foreach ($dataID as $order => $profileID) {
                $statement .= ' WHEN «mainEntity.primaryKey.name» = ' . intval($profileID) . ' THEN ' . (intval($order) + 1);
            }

            $statement .= ' ELSE ' . 0 . ' END Where «mainEntity.primaryKey.name» IN(' . implode(',', $dataID) . ')';
            $db->setQuery($statement);
            $response = $db->execute();
        
            if ($response) {
                $query = $db->getQuery(true);
                $query->select('`«mainEntity.primaryKey.name»`, `ordering`')->from('#__«com.name»_«indexpage.entities.get(0).name»');
                $db->setQuery($query);

                return $db->loadObjectList();
            }

            return false;
        }
    '''

    def CharSequence genAdminViewDisplay() '''
        /**
         * loads model data into view context
         *
         * @param   string  $tpl  the name of the template to be used
         *
         * @return void
         * @generated
         */
        public function display($tpl = null)
        {
            $this->state         = $this->get('State');
            $this->items         = $this->get('Items');
            $this->pagination    = $this->get('Pagination');
            $this->filterForm    = $this->get('FilterForm');
            $this->activeFilters = $this->get('ActiveFilters');
        
            // Check for errors.
            if (count($errors = $this->get('Errors'))) {
                throw new \JViewGenericdataexception(implode("\n", $errors), 500);
            }
        
            $this->addToolbar();
            $this->sidebar = \JHtmlSidebar::render();
            
            return parent::display($tpl);
        }
    '''

    def CharSequence genAdminViewAddtoolbar() {
        // Add static language for all the status changes.
        Slug.addLanguage(com.languages, newArrayList("com", com.name, "N", "ITEMS", "PUBLISHED"), StaticLanguage.ITEMS_PUBLISHED.value)
        Slug.addLanguage(com.languages, newArrayList("com", com.name, "N", "ITEMS", "UNPUBLISHED"), StaticLanguage.ITEMS_UNPUBLISHED.value)
        Slug.addLanguage(com.languages, newArrayList("com", com.name, "N", "ITEMS", "ARCHIVED"), StaticLanguage.ITEMS_ARCHIVED.value)
        Slug.addLanguage(com.languages, newArrayList("com", com.name, "N", "ITEMS", "TRASHED"), StaticLanguage.ITEMS_TRASHED.value)
        
        return '''
            /**
             * Add the page title and toolbar.
             *
             * @since 1.6
             * @generated
             */
            protected function addToolbar()
            {
                $canDo = «com.componentHelperClassName»::getActions(
                    '«Slug.nameExtensionBind("com", com.name)»',
                    '«details»',
                    $this->state->get('filter.«mainEntity.primaryKey.name»')
                );

                $app    = Factory::getApplication();
                $user   = $app->getIdentity();
            
                // Get the toolbar object instance
                $toolbar = Toolbar::getInstance('toolbar');

                ToolBarHelper::title(Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "TITLE", indexpage.name), indexpage.name)»'));
            
                if ($canDo->get('core.create') || count($user->getAuthorisedCategories('com_content', 'core.create')) > 0) {
                    $toolbar->addNew('«details».add');
                }
            
                if ($canDo->get('core.edit.state') || $canDo->get('core.execute.transition')) {
                    $dropdown = $toolbar->dropdownButton('status-group')
                        ->text('JTOOLBAR_CHANGE_STATUS')
                        ->toggleSplit(false)
                        ->icon('fa fa-globe')
                        ->buttonClass('btn btn-info')
                        ->listCheck(true);

                    $childBar = $dropdown->getChildToolbar();

                    if ($canDo->get('core.execute.transition')) {
                        $childBar->publish('«indexpage.name».publish')->listCheck(true);

                        $childBar->unpublish('«indexpage.name».unpublish')->listCheck(true);
                    }

                    if ($canDo->get('core.execute.transition')) {
                        $childBar->archive('«indexpage.name».archive')->listCheck(true);
                    }

                    if ($canDo->get('core.execute.transition')) {
                        $childBar->trash('«indexpage.name».trash')->listCheck(true);
                    }
                }

                if ($this->state->get('filter.condition') == «com.name.toFirstUpper»Component::CONDITION_TRASHED && $canDo->get('core.delete')) {
                    $toolbar->delete('«indexpage.name».delete')
                        ->text('JTOOLBAR_EMPTY_TRASH')
                        ->message('JGLOBAL_CONFIRM_DELETE')
                        ->listCheck(true);
                }

                if ($user->authorise('core.admin', '«Slug.nameExtensionBind("com", com.name)»') || $user->authorise('core.options', '«Slug.nameExtensionBind("com", com.name)»')) {
                    $toolbar->preferences('«Slug.nameExtensionBind("com", com.name)»');
                }
            }
        '''
    }

    def private CharSequence genAdminViewLayoutFilters() '''
        <?php
            echo LayoutHelper::render('joomla.searchtools.default', array('view' => $this));
        ?>
    '''

    def private CharSequence genAdminViewLayoutData(EList<ExtendedAttribute> column) '''
        <?php
        if (empty($this->items)) : ?>
            <div class="alert alert-warning">
                <?php echo JText::_('JGLOBAL_NO_MATCHING_RESULTS'); ?>
            </div>
        <?php
        else : ?>
            <table class="table table-striped" id="«indexpage.name.toFirstUpper»List">
                <caption id="captionTable" class="sr-only">
                    <?php
                    echo Text::_('«Slug.addLanguage(com.languages, newArrayList("COM", com.name, "TABLE", "CAPTION"), '''Table of «indexpage.name»''')»'); ?>,
                    <?php
                    echo Text::_('JGLOBAL_SORTED_BY'); ?>
                </caption>
                <thead>
                    <tr>
                        <?php
                        if (isset($this->items[0]) && property_exists($this->items[0], 'ordering')) : ?>
                            <th scope="col" style="width:1%" class="text-center d-none d-md-table-cell">
                                <?php
                                echo HTMLHelper::_(
                                    'searchtools.sort',
                                    '',
                                    '«this.mainEntity.name».ordering',
                                    $listDirn,
                                    $listOrder,
                                    null,
                                    'asc',
                                    'JGRID_HEADING_ORDERING',
                                    'icon-menu-2'
                                ); ?>
                            </th>
                        <?php $columns++; ?>
                        <?php
                        endif; ?>
                        <td style="width:1%" class="text-center">
                            <?php echo HTMLHelper::_('grid.checkall'); ?>
                        </td>
                        <?php
                        if (isset($this->items[0]) && property_exists($this->items[0], 'state')) : ?>
                            <th scope="col" style="width:1%; min-width:85px" class="text-center">
                                <?php
                                echo HTMLHelper::_(
                                    'searchtools.sort',
                                    'JSTATUS',
                                    '«this.mainEntity.name».state',
                                    $listDirn,
                                    $listOrder
                                ); ?>
                            </th>
                        <?php $columns++; ?>
                        <?php
                        endif; ?>
                        «FOR ExtendedAttribute attr : column»
                            <th scope="col">
                                <?php
                                echo HTMLHelper::_(
                                    'searchtools.sort',
                                    '«Slug.addLanguage(com.languages, newArrayList("com", com.name, "FORM", "LBL", mainEntity.name, attr.name), attr.name)»',
                                    '«this.mainEntity.name».«attr.name»',
                                    $listDirn,
                                    $listOrder
                                ); ?>
                            </th>
                        «ENDFOR»
                    </tr>
                </thead>
                <tbody <?php if ($saveOrder) :?> class="js-draggable" data-url="<?php echo $saveOrderingUrl; ?>" data-direction="<?php echo strtolower($listDirn); ?>" data-nested="true"<?php endif; ?>>
                    <?php
                    foreach ($this->items as $i => $item) :
                        $canCreate  = $user->authorise('core.create', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
                        $canEdit    = $user->authorise('core.edit', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
                        $canCheckin = $user->authorise('core.manage', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
                        $canChange  = $user->authorise('core.edit.state', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
                        ?>
                        <tr class="row<?php echo $i % 2; ?>">
                        <?php
                        if (isset($this->items[0]) && property_exists($this->items[0], 'ordering')) : ?>
                            <td class="order text-center d-none d-md-table-cell">
                                <?php
                                $iconClass = '';
                                if (!$canChange)
                                {
                                    $iconClass = ' inactive';
                                }
                                elseif (!$saveOrder)
                                {
                                    $iconClass = ' inactive tip-top hasTooltip" title="' . HTMLHelper::_('tooltipText', 'JORDERINGDISABLED');
                                }
                                ?>
                                <span class="sortable-handler<?php echo $iconClass; ?>">
                                    <span class="icon-menu" aria-hidden="true"></span>
                                </span>
                                <?php if ($canChange && $saveOrder) : ?>
                                    <input type="text" style="display:none" name="order[]" size="5"
                                        value="<?php echo $item->ordering; ?>" class="width-20 text-area-order">
                                <?php endif; ?>
                            </td>
                        <?php
                        endif; ?>
                        <td class="center hidden-phone">
                            <?php echo HTMLHelper::_('grid.id', $i, $item->«mainEntity.primaryKey.name»); ?>
                        </td>
                        <?php
                        if (isset($this->items[0]->state)) : ?>
                            <td class="center">
                                <?php
                                echo HTMLHelper::_(
                                    'jgrid.published',
                                    $item->state,
                                    $i,
                                    '«indexpage.name.toLowerCase».',
                                    $canChange,
                                    'cb'
                                ); ?>
                            </td>
                        <?php
                        endif; ?>
                        «genModelAttributeReference(column, indexpage, com)»
                        </tr>
                    <?php
                    endforeach; ?>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="<?php echo $columns;?>">
                            <?php echo $this->pagination->getListFooter(); ?>
                        </td>
                    </tr>
                </tfoot>
            </table>
        <?php
        endif; ?>
    '''

    def getextendedTableColumnListSize() {
        return indexpage.extendedTableColumnList.size
    }

    def CharSequence genAdminViewLayoutForm() ''' 
        <form
            action="<?php echo Route::_('index.php?option=«Slug.nameExtensionBind("com", com.name)»&view=«indexpage.name»'); ?>"
            method="post"
            name="adminForm"
            id="adminForm">
            <div class="row">
                <div class="col-md-12">
                    <?php
                    if (!empty($this->sidebar)) : ?>
                        <div id="j-sidebar-container" class="span2">
                            <?php echo $this->sidebar; ?>
                        </div>
                        <div id="j-main-container" class="j-main-container span10">
                    <?php
                    else : ?>
                        <div id="j-main-container" class="j-main-container">
                    <?php
                    endif; ?>
                        «genAdminViewLayoutFilters»
                        «genAdminViewLayoutData(indexpage.extendedTableColumnList)»
                        <input type="hidden" name="task" value="" />
                        <input type="hidden" name="boxchecked" value="0" />
                        <?php echo HTMLHelper::_('form.token'); ?>
                    </div>
                </div>
            </div>
        </form>
    '''

    def CharSequence genAdminViewLayoutHeader() '''
        $app = Factory::getApplication();
        $user = $app->getIdentity();
        $userId = $user->get('id');
        $listOrder = $this->state->get('list.ordering');
        $listDirn = $this->state->get('list.direction');
        $canOrder = $user->authorise('core.edit.state', '«Slug.nameExtensionBind("com", com.name).toLowerCase»');
        $saveOrder = $listOrder == '«this.mainEntity.name».ordering';
        $model = $this->getModel();
        if ($saveOrder) {
            $saveOrderingUrl = 'index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«indexpage.name.toLowerCase()».saveOrderAjax&tmpl=component&' . Session::getFormToken() . '=1';
            HTMLHelper::_('draggablelist.draggable');
        }
        ?>
    '''

    def CharSequence genAdminModelPopulateState() '''
        /**
         * Method to auto-populate the model state.
         *
         * Note. Calling getState in this method will result in recursion.
         */
        protected function populateState($ordering = '«indexpage.entities.get(0).name».«mainEntity.primaryKey.name»', $direction = 'asc')
        {
            // Load the parameters.
            $params = ComponentHelper::getParams('«Slug.nameExtensionBind("com", com.name.toLowerCase)»');
            $this->setState('params', $params);
        
            // List state information.
            parent::populateState($ordering, $direction);
        }
    '''

    public def CharSequence genGetIdOfReferenceItem() '''
        /**
         * Method to get the id of Reference
         * @param  String  $linkName    containt the name of a Attribute
         * @param  String  $attrvaluen  containt the value of a Row
         *
         * @return Integer the ID of a Row
         *
         */
        public function getIdOfReferenceItem($linkName, $attrvalue)
        {
            $dbtable = $this->entitiesRef["$linkName"]["db"];
            $attribute = $this->entitiesRef["$linkName"]["refattr"];
            $db = $this->getDbo();
            $query = $db->getQuery(true);
            $key = $this->entitiesRef["$linkName"]["foreignPk"];
            $query->select($key)
                ->from($dbtable);
            foreach ($attribute as $index => $attributItem) {
                $query->where($attributItem . " like '".$attrvalue->$index."'");
            }
        
            $db->setQuery($query);
            $result = $db->loadObject();
            return intval($result->$key);
        }
    '''
    
    def genAdminViewSortFields(EList<ExtendedAttribute> column) {
        var sortFields = newArrayList(
            ''''«this.mainEntity.name».ordering' => Text::_('JGRID_HEADING_ORDERING')''',
            ''''«this.mainEntity.name».state' => Text::_('JSTATUS')'''
        )
        
        sortFields.addAll(column.map[ attr |
            ''''«this.mainEntity.name».«attr.name»' => Text::_('«Slug.addLanguage(com.languages, newArrayList("com", com.name, "FORM", "LBL", mainEntity.name, attr.name), attr.name)»')'''
        ])
        
        return '''
        /**
         * Returns an array of fields the table can be sorted by
         *
         * @return  array  Array containing the field name to sort by as the key and display text as value
         *
         * @since   3.0
         */
        protected function getSortFields()
        {
            return array(
                «sortFields.join(''',
                ''')»
            );
        }
        '''
    }
}
