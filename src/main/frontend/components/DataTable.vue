<template>
<div class="datatable">
    <header v-if="pagination || search"  :class="{'text-right': !pagination && search}">
        <div v-if="pagination"  class="datatable-options alignMiddle-half">
            <label>Show
                <select v-model="paginationLength">
                    <option>10</option>
                    <option>25</option>
                    <option>50</option>
                    <option>100</option>
                    </select>
                 Entries</label>
            </div>
        <div v-if="search" class="datatable-search alignMiddle-half">
            <label>Search</label>
            <input type="text" class="form-control" v-model="searchInput"/>
        </div>
    </header>
    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <slot name="before-headings"></slot>
            <th v-for="item, itemIndex in headings">{{item}} <button v-if="columnSorting" class="btn btn-clear btn-small" @click="sortColumn(itemIndex)"><i :class="['fas', sortButtonClass(itemIndex)]"></i></button></th>
            <slot name="after-headings"></slot>
            </tr>
        </thead>
        <tbody>
        <slot name="table-body" v-bind:rows="currentRows">
            <tr v-for="(row, rowIndex) in currentRows">
                <slot name="before-data" v-bind:rowIndex="rows.indexOf(row)"></slot>
                <td v-for="item in row">{{item}}</td>
                <slot name="after-data" v-bind:rowIndex="rows.indexOf(row)" v-bind:rowData="row"></slot>
                </tr>
            </slot>
        </tbody>
        <tfoot v-if="tableFooter">
        <tr>
            <slot name="before-footer-headings"></slot>
            <th v-for="item, itemIndex in headings">{{item}}</th>
            <slot name="after-footer-headings"></slot>
            </tr>
        </tfoot>
        </table>
    <footer>
        <div class="datatable-indicator alignMiddle-half">
            <p>Showing {{currentRows.length}} of {{filteredRows.length}} entries</p>
        </div>
        <div v-if="pagination" class="datatable-pagination alignMiddle-half">
            <ul class="pagination">
                <li class="page-item"><button :disabled="currentPage === 0" @click="changePage(currentPage - 1)" class="page-link btn" aria-label="Previous"><i class="fas fa-angle-double-left" aria-hidden="true"></i></button></li>
                <li v-for="page in numPages" :class="[{active: currentPage === (page - 1)}, 'page-item']"><a @click="changePage(page - 1)" class="page-link btn btn-clear">{{page}}</a></li>
                <li class="page-item"><button :disabled="currentPage === numPages - 1" @click="changePage(currentPage + 1)" class="page-link btn" aria-label="Next"><i class="fas fa-angle-double-right" aria-hidden="true"></i></button></li>
            </ul>
        </div>
    </footer>
</div>
</template>

<script>
    function escapeRegExp (text) {
        return text.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, '\\$&');
    }

    export default {
        props: {
            headings: {
                type: [Array, Object],
                required: true
            },
            rows: {
                type: [Array, Object],
                required: true
            },
            search: {
                type: Boolean,
                default: true
            },
            columnSorting: {
                type: Boolean,
                default: true
            },
            pagination: {
                type: Boolean,
                default: true
            },
            defaultPaginationLength: {
                type: Number,
                default: 10,
                validator: function (value) {
                    return value > 0;
                }
            },
            tableFooter: {
                type: Boolean,
                default: false
            },
        },
        data: function () {
            return {
                currentPageIndex: 0,
                searchInput: '',
                sortedBy: null,
                sortAscending: null,
                paginationLength: this.defaultPaginationLength
            }
        },
        methods: {
            changePage: function (page) {
                if (page >= 0 && page < this.currentRows.length) {
                    this.currentPageIndex = page;
                }
            },
            sortColumn: function (colIndex) {
                this.sortedBy = colIndex;

                if (this.sortAscending === true) {
                    this.sortAscending = false;
                } else if (this.sortAscending === false) {
                    this.sortAscending = null;
                } else {
                    this.sortAscending = true
                }
            },
            filterRows: function (rows) {
                if (this.searchInput.length > 0) {
                    var escapedInputText = escapeRegExp(this.searchInput.toLowerCase());
                    return rows.filter(function (row) {
                        return Object.keys(row).filter(function (key) {
                            var colVal = String(row[key]);
                            return colVal.toLowerCase().search(escapedInputText) !== -1;
                        }).length > 0;
                    });
                }

                return this.rows.slice();
            },
            paginateRows: function (rows) {
                if (this.pagination) {
                    var startIndex = 1 * this.currentPage * this.paginationLength;
                    var endIndex = this.paginationLength * (this.currentPage + 1);

                    return rows.slice(startIndex, endIndex);
                } else {
                    return rows.slice();
                }
            },
            sortButtonClass: function (colIndex) {
                if (this.sortAscending === null || this.sortedBy === null || colIndex !== this.sortedBy) {
                    return 'fa-sort';
                } else if (this.sortAscending) {
                    return 'fa-sort-up'
                } else {
                    return 'fa-sort-down';
                }
            },
        },
        computed: {
            filteredRows: function () {
                if (this.search) {
                    return this.filterRows(this.rows);
                } else {
                    return this.rows.slice();
                }
            },
            currentPage: function () {
                return this.currentPageIndex > this.numPages? 0 : this.currentPageIndex;
            },
            currentRows: function () {
                var currentRows = this.filterRows(this.rows);

                var self = this;

                if (this.columnSorting && this.sortedBy !== null && this.sortAscending !== null) {
                    currentRows.sort(function (a, b) {
                        if (a[self.sortedBy] > b[self.sortedBy]) {
                            return self.sortAscending? 1 : -1;
                        } else if (a[self.sortedBy] < b[self.sortedBy]) {
                            return  self.sortAscending? -1 : 1;
                        } else {
                            return 0
                        }
                    });
                }

                return this.paginateRows(currentRows);
            },
            numPages: function () {
                return Math.ceil(this.filteredRows.length / this.paginationLength);
            }
        }
    }
</script>

<style>
.datatable > header {
    margin-bottom: 1em;
}

.datatable-options label {
    font-weight: 400;
}

.datatable-options select {
    margin-left: 5px;
    margin-right: 5px;
}

.datatable-search,
.datatable-pagination {
    text-align: right;
}

.datatable-pagination .pagination {
    justify-content: flex-end;
}

.datatable-search label {
    margin-right: 1em;
}

.datatable-search .form-control {
    max-width: 300px;
    display: inline-block;
}

.datatable-indicator p {
    margin: 0;
}

.datatable-pagination .pagination {
    margin: 0;
    padding: 0;
}

.datatable-pagination .pagination li {
    list-style: none;
}

.datatable-pagination .pagination .page-link {
    display: inline-block;
    margin: 0 2px;
}
</style>