import { Component, OnInit } from '@angular/core';
import { SearchComponentService } from 'src/app/services/cardSearchBar/search-bar.service'
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-footer-search',
    templateUrl: './footer-search.component.html',
    styleUrls: ['./footer-search.component.scss']
})
export class FooterSearchComponent implements OnInit {

    private searchbarComponenet: any;
    private searchedTerm: any;




    constructor(private SearchComponentService: SearchComponentService, private activateRoute: ActivatedRoute) { }
    ngOnInit() {

        this.searchedTerm = this.activateRoute.snapshot.paramMap.get('searchTerm')
        this.SearchComponentService.getSearch(this.searchedTerm)
            .subscribe(data => {
                this.searchbarComponenet = data
            });

    }
}
